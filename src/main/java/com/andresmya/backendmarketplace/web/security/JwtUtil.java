package com.andresmya.backendmarketplace.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final int JWT_MS_DURATION = 5 * 60 * 60 * 1000; // 5 hours to milliseconds

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(getNewExpirationDate())
                .signWith(SignatureAlgorithm.HS256, getSignKey()).compact();
    }

    public boolean isJwtValid(String jwt, UserDetails userDetails){
        return userDetails.getUsername().equals(getUsernameFromJwt(jwt))
                && !isJwtExpired(jwt);
    }

    public String getUsernameFromJwt(String jwt) {
        return getClaims(jwt).getSubject();
    }

    public static Date getNewExpirationDate() {
        return new Date(System.currentTimeMillis() + JWT_MS_DURATION);
    }

    private boolean isJwtExpired(String jwt) {
        return getClaims(jwt).getExpiration().before(new Date());
    }

    private Claims getClaims (String jwt) {
        return Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(jwt).getBody();
    }

    private String getSignKey() {
        return secret;
    }
}
