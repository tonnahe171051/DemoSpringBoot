package com.example.layered_architecture_demo.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    @Value("${app.jwt.secret}")  
    private String jwtSecret;

    @Value("${app.jwt.expiration}")  
    private int jwtExpirationMs;

    public String generateToken(Authentication auth) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setSubject(auth.getName())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
