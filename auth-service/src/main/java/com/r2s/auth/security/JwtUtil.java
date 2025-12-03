package com.r2s.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
=======
import org.springframework.stereotype.Component;

import java.security.Key;
>>>>>>> 4fad818 (feat(auth): Update application properties to use PostgreSQL config)
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
<<<<<<< HEAD

    // 🔐 Tạo key với kích thước >= 256bit (an toàn cho HS256)
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 🔥 Tạo JWT token
    public String generateToken(String username) {
=======

    private final String SECRET_KEY = "pFBtU0xHRm1NRkpvS1pHV1M2aEJFamdYTlNMQkdRbVRWS0RIa3JGWQ==";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate token chứa username + role
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

>>>>>>> 4fad818 (feat(auth): Update application properties to use PostgreSQL config)
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
<<<<<<< HEAD
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
                .signWith(SECRET_KEY)
                .compact();
    }

    // Lấy username từ token
=======
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

>>>>>>> 4fad818 (feat(auth): Update application properties to use PostgreSQL config)
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

<<<<<<< HEAD
    // Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
=======
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
>>>>>>> 4fad818 (feat(auth): Update application properties to use PostgreSQL config)
    }

    // Lấy Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
