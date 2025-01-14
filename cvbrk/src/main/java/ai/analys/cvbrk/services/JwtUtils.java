package ai.analys.cvbrk.services;

import ai.analys.cvbrk.entity.Role;
import ai.analys.cvbrk.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.Arrays;

@Service
public class JwtUtils {

    private final SecretKey key;
    private static final long ACCESS_TOKEN_EXPIRATION_SECONDS = 3600; // 1 heure
    private static final long REFRESH_TOKEN_EXPIRATION_SECONDS = 604800; // 7 jours

    public JwtUtils() {
        String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getEncoder().encode(secretString.getBytes()); // Correction : Encodage en Base64
        this.key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }
    public String generateAccessToken(UserDetails userDetails) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusSeconds(ACCESS_TOKEN_EXPIRATION_SECONDS);
        User user = (User) userDetails;
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", Arrays.asList(user.getRole().name()))
                .claim("id", user.getId())
                .setIssuedAt(toDate(now))
                .setExpiration(toDate(expiration))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusSeconds(REFRESH_TOKEN_EXPIRATION_SECONDS);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(toDate(now))
                .setExpiration(toDate(expiration))
                .signWith(key)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String tokenUsername = extractUsername(token);
        final Long tokenID = extractId(token);
        final String tokenRole = extractRole(token);
        final String userRole = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        return (tokenUsername.equals(userDetails.getUsername())
                && !isTokenExpired(token)
                && tokenRole != null
                && tokenID != null
                && tokenRole.equals(userRole));
    }
    public boolean isRefershTokenValid(String token, UserDetails userDetails) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(userDetails.getUsername())
                && !isTokenExpired(token)
        );
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", List.class).get(0).toString());
    }

    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("role", List.class));
    }
    public Long extractId(String token) {
        return extractClaim(token, claims -> claims.get("id", Long.class));
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
    private boolean isTokenExpired(String token) {
        LocalDateTime expiration = extractExpiration(token);
        return expiration.isBefore(LocalDateTime.now());
    }

    private LocalDateTime extractExpiration(String token) {
        return extractClaim(token, claims ->
                claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}