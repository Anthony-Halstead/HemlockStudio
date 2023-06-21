package com.HemlockStudiosWebsite.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.HemlockStudiosWebsite.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class SecurityConfig {
@Autowired
private final RSAKeyProperties keys;
public SecurityConfig(RSAKeyProperties keys)
{
this.keys = keys;
}
@Bean
public PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
}
@Bean
public AuthenticationManager authManager(UserDetailsService detailsService, PasswordEncoder passwordEncoder){
DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
daoProvider.setUserDetailsService(detailsService);
daoProvider.setPasswordEncoder(passwordEncoder);
return new ProviderManager(daoProvider);
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    System.out.println("In the security filter chain");
    http
            .cors(withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/auth/register").permitAll();
                auth.requestMatchers("/auth/login").permitAll();
                auth.requestMatchers("/auth/confirm").permitAll();
                auth.requestMatchers("/auth/health").permitAll();
                auth.requestMatchers("/auth/registerAdmin").hasRole("ADMIN");
                auth.requestMatchers("/analytics/**").hasRole("ADMIN");
                auth.requestMatchers("/enums/**").permitAll();
                auth.requestMatchers("/product/**").permitAll();
                auth.requestMatchers("/news/createNews").hasRole("ADMIN");
                auth.requestMatchers("/news/update").hasRole("ADMIN");
                auth.requestMatchers("/news/delete/{id}").hasRole("ADMIN");
                auth.requestMatchers("/news/findAll").permitAll();
                auth.requestMatchers("/coupon/createCoupon").hasRole("ADMIN");
                auth.requestMatchers("/coupon/delete/{id}").hasRole("ADMIN");
                auth.requestMatchers("/coupon/applyCouponDiscount").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/coupon/findAll").hasRole("ADMIN");
                auth.requestMatchers("/photo/create").hasRole("ADMIN");
                auth.requestMatchers("/photo/findAll").hasRole("ADMIN");
                auth.requestMatchers("/photo/updatePhoto").hasRole("ADMIN");
                auth.requestMatchers("/photo/deletePhoto/{id}").hasRole("ADMIN");
                auth.requestMatchers("/product/createProduct").hasRole("ADMIN");
                auth.requestMatchers("/product/update").hasRole("ADMIN");
                auth.requestMatchers("/product/delete/{id}").hasRole("ADMIN");
                auth.requestMatchers("/product/findAll").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/admin/**").hasRole("ADMIN");
                auth.requestMatchers("/user/getUser").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/findAll").hasRole("ADMIN");
                auth.requestMatchers("/user/update").hasRole("ADMIN");
                auth.requestMatchers("/user/delete/{id}").hasRole("ADMIN");
                auth.requestMatchers("/user/addProductToFavorites").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/removeProductFromFavorites/{favoriteProductId}").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/addCreditCard").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/removeCreditCard/{creditCardId}").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/updateCreditCard").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/findCreditCards").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/findFavoriteProducts").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/toggleNotification").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/getNotificationStatus").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/setDefaultCreditCard").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/getDefaultCreditCard").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/cart/**").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/email/**").hasAnyRole("ADMIN", "USER");       
                auth.anyRequest().authenticated();
            });
http
.oauth2ResourceServer(server -> server
.jwt()
.jwtAuthenticationConverter(jwtAuthenticationConverter()));
http
.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
return http.build();
}


@Bean
public JwtDecoder jwtDecoder(){
    System.out.println("In the jwt decoder");
return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
}

@Bean
public JwtEncoder jwtEncoder(){
JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
return new NimbusJwtEncoder(jwks);
}
@Bean
public JwtAuthenticationConverter jwtAuthenticationConverter(){
JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
return jwtConverter;
}

} 