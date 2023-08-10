/**
 * The TokenService class is responsible for generating a JWT (JSON Web Token) for authentication
 * purposes.
 */
package com.HemlockStudiosWebsite.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.User;
@Service
public class TokenService {
@Autowired
private JwtEncoder jwtEncoder;

/**
 * The function generates a JSON Web Token (JWT) with specified claims and returns the token value.
 * 
 * @param auth The `auth` parameter is an instance of the `Authentication` class, which represents the
 * authentication information of the current user. It contains details such as the user's principal
 * (typically a `UserDetails` object), credentials, and authorities.
 * @return The method returns a String, which is the generated JWT (JSON Web Token).
 */
public String generateJwt(Authentication auth) {
        Instant now = Instant.now();
        Instant expiryTime = now.plus(24, ChronoUnit.HOURS);  // Token will expire 24 hour from now

        User principal = (User) auth.getPrincipal();
        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiryTime)  // setting expiry time for the token
                .subject(principal.getUsername()) // this will be the username
                .claim("userId", principal.getId()) // add user id as a claim
                .claim("email", principal.getEmail()) // add email as a claim
                .claim("roles", authorities) // roles were already included
                .build();
        JwtEncoderParameters encoderParameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(encoderParameters).getTokenValue();
    }
}