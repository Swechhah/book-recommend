package com.platform.recommendor.app.infrastucture.auth;

import com.platform.recommendor.app.infrastucture.BookRecommendorRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwUtil {

    private final BookRecommendorRepository repository;
//    private final RefreshTokenRepository refreshTokenRepository;
//    private final RefreshTokenMapper tokenMapper;
    private final SecretKey key;
//
//
    JwUtil(@Value("${jwt.secret}") String SECRET_KEY,  BookRecommendorRepository repository ){
        this.repository = repository;
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(String username) {
        return Jwts.builder()
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
//    @Autowired
//    JwUtil(@Value("${jwt.secret}") String SECRET_KEY, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, RefreshTokenMapper tokenMapper) {
//        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
//        this.userRepository = userRepository;
//        this.refreshTokenRepository = refreshTokenRepository;
//        this.tokenMapper = tokenMapper;
//
//    }
//
//    public String generateToken(User userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        Set<String> authorities = userDetails
//                .getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toSet());
//
//        Set<String> roles = authorities.stream()
//                .filter(auth -> auth.startsWith("ROLE_"))
//                .map(auth -> auth.replace("ROLE_", ""))
//                .collect(Collectors.toSet());
//
//        Set<String> permissions = authorities.stream()
//                .filter(auth -> !auth.startsWith("ROLE_"))
//                .collect(Collectors.toSet());
//
//        claims.put("roles", roles);
//        claims.put("permissions", permissions);
//        return createToken(claims, userDetails);
//    }
//
//    public String createToken(Map<String, Object> extraClaims, UserDetails user) {
//        return (Jwts.builder().claims().add(extraClaims).and()
//                .subject(user.getUsername())
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
//                .signWith(key).compact());
//
//    }
//
//    public String extractUsername(String token) {
//        Claims claims = getClaims(token);
//        return claims.getSubject();
//    }
//
//    public String getToken(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            return authHeader.substring(7);
//        } else {
//            throw new ResourceNotFoundException("no tokens found");
//        }
//
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String token) {
//        Claims claims = getClaims(token);
//        final ObjectMapper objectMapper = new ObjectMapper();
//
//        Object rolesObject = claims.get("roles");
//        List<String> roles = objectMapper.convertValue(rolesObject, new TypeReference<List<String>>() {
//        });
//
//        Object permissionsObject = claims.get("permissions");
//        List<String> permissions = objectMapper.convertValue(permissionsObject, new TypeReference<List<String>>() {
//        });
//
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//
//        if (roles != null) {
//            for (String role : roles) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//            }
//        }
//
//        if (permissions != null) {
//            for (String permission : permissions) {
//                authorities.add(new SimpleGrantedAuthority(permission));
//            }
//        }
//        return authorities;
//    }
//
//    public Claims getClaims(String token) {
//        return Jwts.parser()
//                .verifyWith(key)
//                .build().parseSignedClaims(token)
//                .getPayload();
//    }
//
//    public boolean isTokenExpired(String token) {
//        Date expirationDate = getClaims(token).getExpiration();
//        if(expirationDate.before(new Date())){
//            throw new InvalidTokenException("Token is expired");
//        }else return false;
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        if (!isTokenExpired(token)) {
//            Claims claims = getClaims(token);
//            return extractUsername(token).equals(userDetails.getUsername());
//        } else return false;
//    }
//
//    @Transactional
//    public RefreshTokenDto generateRefreshToken(User user) {
//        Optional<RefreshToken> optional = refreshTokenRepository.findByUserId(user.getId());
//        optional.ifPresent(this::deleteRefreshToken);
//        RefreshToken refreshToken = RefreshToken.builder()
//                .token(UUID.randomUUID().toString())
//                .expiryDate(LocalDateTime.now().plusSeconds(86400))
//                .isRevoked(false)
//                .user(user)
//                .build();
//        refreshTokenRepository.saveAndFlush(refreshToken);
//        return tokenMapper.toRefreshTokenDto(refreshToken);
//    }
//
//    public void deleteRefreshToken(RefreshToken refreshToken){
//        refreshTokenRepository.delete(refreshToken);
//        refreshTokenRepository.flush();
//    }
//
//    public void revokeRefreshToken(User user) {
//
//    }
}


