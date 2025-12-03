package com.platform.recommendor.app.infrastucture.auth;

import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Component
public class JwUtil {

    private final BookRecommendorRepository repository;
    private final SecretKey key;

    JwUtil(@Value("${jwt.secret}") String SECRET_KEY,  BookRecommendorRepository repository ){
        this.repository = repository;
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        Optional<UserModel> user = repository.getUserByUsername(username);
        claims.put("role", user.get().getRole().getRole());
        return Jwts.builder()
                .setClaims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 60 * 60 * 1000 ))
                .signWith(key)
                .compact();
    }
    public Claims extractClaimByToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build().parseSignedClaims(token)
                .getPayload();

    }
    public String extractUsername(String token) {
        return  extractClaimByToken(token).getSubject();
    }
    public boolean isTokenExpired(String token) {
        Date expiration = extractClaimByToken(token).getExpiration();
        return expiration.before(new Date());
    }
    public boolean validateToken(String token, String username) {
        Claims claims = extractClaimByToken(token);
        String tokenUsername = claims.getSubject();
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
}

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build().parseSignedClaims(token)
                .getPayload();

    }
}


