package com.staj.liquibasepractice.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String SECRET_KEY;

    // Token üret
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // 2 saat
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Token doğrula
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Token'dan kullanıcı adını çıkar
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Token süresi geçmiş mi?
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Expiration'ı al
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // Tüm claim'leri al
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Şifreleme anahtarını al
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
