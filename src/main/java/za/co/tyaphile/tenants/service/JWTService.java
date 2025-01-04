package za.co.tyaphile.tenants.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final String SECRET_KEY;
    private final String PASSWORD = "Password";
    private final int MINUTES = 1440;

    public JWTService() {
        this.SECRET_KEY = new StandardPasswordEncoder().encode(PASSWORD);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Map<String, Object> generateToken(String username) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + (1000 * 60 * MINUTES));
        String token = Jwts.builder()
                .issuedAt(issuedAt)
                .expiration(expiration)
                .subject(username)
                .signWith(getKey())
                .compact();

        return Map.of("access_token", token,
                "issued_at", issuedAt,
                "expiration", expiration);
    }

    public boolean isValid(UserDetails userDetails, String token) {
        String user = extractUsername(token);
        return user.equals(userDetails.getUsername()) && !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}