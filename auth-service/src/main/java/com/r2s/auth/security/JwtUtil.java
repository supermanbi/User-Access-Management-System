package com.r2s.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

  private final String SECRET_KEY = "pFBtU0xHRm1NRkpvS1pHV1M2aEJFamdYTlNMQkdRbVRWS0RIa3JGWQ==";

  public String generateToken(String username, Map<String, Object> claims) {
    Instant now = Instant.now();
    return Jwts.builder()
        .setSubject(username)
        .addClaims(claims)
        .setIssuedAt(Date.from(now))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY)
        .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    return extractUsername(token).equals(userDetails.getUsername());
  }
}
