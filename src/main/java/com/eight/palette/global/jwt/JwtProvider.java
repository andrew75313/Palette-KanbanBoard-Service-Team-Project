package com.eight.palette.global.jwt;

import com.eight.palette.domain.user.entity.User;
import com.eight.palette.domain.user.entity.UserRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

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
    public String createAccessToken(String username, UserRoleEnum role) {

        return generateToken(username,role,tokenExpiration);
    }

    // 리프레시 토큰 생성
    public String createRefreshToken(String username) {
        return generateRefreshToken(username,refreshTokenExpiration);
    }

    public String generateToken(String username, UserRoleEnum role, long expiration) {
        return Jwts.builder()
                .setSubject(username) // 토큰 주체
                .claim("role",role.getAuthority())
                .setIssuedAt(new Date()) // 토큰 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 토큰 만료시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(String username, long expiration) {
        return Jwts.builder()
                .setSubject(username) // 토큰 주체
                .claim("type", "refresh") // 리프레시 토큰 유형 클레임 추가
                .setId(UUID.randomUUID().toString()) // 고유한 ID로 설정
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

    public String getRoleFromToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return claims.getBody().get("role", String.class);
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

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

}