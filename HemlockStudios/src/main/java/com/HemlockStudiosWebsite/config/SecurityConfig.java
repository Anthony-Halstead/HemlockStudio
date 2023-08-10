/**
 * The SecurityConfig class is a configuration class for Spring Security that sets up authentication,
 * authorization, and JWT decoding/encoding.
 */
package com.HemlockStudiosWebsite.config;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
// The `public SecurityConfig(RSAKeyProperties keys)` constructor is initializing the `keys` field of
// the `SecurityConfig` class with the value passed as a parameter. This constructor is used to inject
// the `RSAKeyProperties` bean into the `SecurityConfig` class.
public SecurityConfig(RSAKeyProperties keys)
{
this.keys = keys;
}
/**
 * The function returns a BCryptPasswordEncoder object to be used as a password encoder in Java.
 * 
 * @return The method is returning a new instance of the BCryptPasswordEncoder class, which is a
 * PasswordEncoder implementation.
 */
@Bean
public PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
}
/**
 * The function creates and returns an instance of AuthenticationManager with a
 * DaoAuthenticationProvider.
 * 
 * @param detailsService The UserDetailsService is an interface provided by Spring Security that is
 * responsible for retrieving user details from a data source. It typically interacts with a database
 * or any other data source to fetch user information such as username, password, and authorities.
 * @param passwordEncoder The passwordEncoder parameter is an instance of a class that implements the
 * PasswordEncoder interface. This interface is used to encode and decode passwords. It is typically
 * used to securely store and compare passwords in a database.
 * @return The method is returning an instance of the AuthenticationManager.
 */
@Bean
public AuthenticationManager authManager(UserDetailsService detailsService, PasswordEncoder passwordEncoder){
DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
daoProvider.setUserDetailsService(detailsService);
daoProvider.setPasswordEncoder(passwordEncoder);
return new ProviderManager(daoProvider);
}

/**
 * This function configures the security filter chain for an HTTP server, specifying the access rules
 * for different endpoints based on user roles.
 * 
 * @param http The `http` parameter is an instance of `HttpSecurity`, which is used to configure the
 * security settings for the application.
 * @return The method is returning a SecurityFilterChain object.
 */
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
                auth.requestMatchers("/news/createNews").hasRole("ADMIN");
                auth.requestMatchers("/news/update").hasRole("ADMIN");
                auth.requestMatchers("/news/delete/{id}").hasRole("ADMIN");
                auth.requestMatchers("/news/findAll").permitAll();
                auth.requestMatchers("/photo/create").hasRole("ADMIN");
                auth.requestMatchers("/photo/findAll").hasRole("ADMIN");
                auth.requestMatchers("/photo/updatePhoto").hasRole("ADMIN");
                auth.requestMatchers("/photo/deletePhoto/{id}").hasRole("ADMIN");
                auth.requestMatchers("/user/getUser").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/findAll").hasRole("ADMIN");
                auth.requestMatchers("/user/update").hasRole("ADMIN");
                auth.requestMatchers("/user/delete/{id}").hasRole("ADMIN");
                auth.requestMatchers("/user/toggleNotification").hasAnyRole("ADMIN", "USER");
                auth.requestMatchers("/user/getNotificationStatus").hasAnyRole("ADMIN", "USER");
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

/**
 * The function creates a JwtDecoder object using a public key and returns it.
 * 
 * @return The method is returning a JwtDecoder object.
 */
@Bean
public JwtDecoder jwtDecoder(){
    System.out.println("In the jwt decoder");
return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
}

/**
 * The function creates a JWT encoder using RSA keys.
 * 
 * @return The method is returning a JwtEncoder object.
 */
@Bean
public JwtEncoder jwtEncoder(){
JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
return new NimbusJwtEncoder(jwks);
}
/**
 * The function returns a JwtAuthenticationConverter object with a JwtGrantedAuthoritiesConverter that
 * sets the authorities claim name and authority prefix.
 * 
 * @return The method is returning an instance of JwtAuthenticationConverter.
 */
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