package com.HemlockStudiosWebsite.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.User;
@Service
public class TokenService {
@Autowired
private JwtEncoder jwtEncoder;
@Autowired
private JwtDecoder jwtDecoder;

public String generateJwt(Authentication auth) {
    Instant now = Instant.now();
    User principal = (User) auth.getPrincipal();
    List<String> authorities = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .subject(principal.getUsername()) // this will be the username
            .claim("userId", principal.getId()) // add user id as a claim
            .claim("email", principal.getEmail()) // add email as a claim
            .claim("roles", authorities) // roles were already included
            .build();
    JwtEncoderParameters encoderParameters = JwtEncoderParameters.from(claims);
    return jwtEncoder.encode(encoderParameters).getTokenValue();
}
}