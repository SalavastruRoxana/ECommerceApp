package com.ECommerceApp.ECommerceApp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTGenerator {

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + SecurityConstants.JWT_TOKEN_VALIDITY); // 1 000

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
                .compact();
        return token;
    }

    private Key key (){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SecurityConstants.JWT_SECRET));
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //https://medium.com/@Lakshitha_Fernando/jwt-spring-security-6-and-spring-boot-3-with-simple-project-819d84e09af2
    public boolean validateToken(String token) {
        try{
            Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
            return true;
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature");
        }

    }
}
