package com.eight.palette.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "Global Exception")
@Component
public class JwtProvider {

    private final String secretKey;

    @Value("${jwt.access.token.expiration}")
    long tokenExpiration;

    @Value("${jwt.refresh.token.expiration}")
    long refreshTokenExpiration;


    public JwtProvider(@Value("${jwt.secret.key}") String secretKey) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 액세스 토큰 생성
    public String createAccessToken(String username) {

        return generateToken(username, tokenExpiration);
    }

    // 리프레시 토큰 생성
    public String createRefreshToken(String username) {
        return generateToken(username, refreshTokenExpiration);
    }

    public String generateToken(String username, long expiration) {
        return Jwts.builder()
                .setSubject(username) // 토큰 주체
                .setIssuedAt(new Date()) // 토큰 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 토큰 만료시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token);
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String refreshToken(String refreshToken) {
        if (validateRefreshToken(refreshToken)) {
            String username = getUsernameFromToken(refreshToken);
            return createAccessToken(username);
        } else {
            throw new IllegalArgumentException("Refresh token이 만료 또는 유효하지 않음");
        }
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }


}