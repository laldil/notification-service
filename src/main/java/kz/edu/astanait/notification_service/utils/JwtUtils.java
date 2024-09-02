package kz.edu.astanait.notification_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kz.edu.astanait.notification_service.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    @Value("${settings.jwt.secret}")
    private String jwtSecret;

    @Value("${settings.jwt.accessLifetime}")
    private Duration accessTokenLifetime;

    @Value("${settings.jwt.refreshLifetime}")
    private Duration refreshTokenLifetime;

    public String generateAccessToken(UserEntity userEntity) {
        var expiration = new Date((new Date()).getTime() + accessTokenLifetime.toMillis());
        return Jwts.builder()
                .subject(userEntity.getEmail())
                .expiration(expiration)
                .claim("id", userEntity.getId())
                .claim("role", userEntity.getRole().toString())
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(UserEntity userEntity) {
        var expiration = new Date((new Date()).getTime() + refreshTokenLifetime.toMillis());
        return Jwts.builder()
                .subject(userEntity.getEmail())
                .expiration(expiration)
                .signWith(getKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expired", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported jwt", e);
        } catch (MalformedJwtException e) {
            log.error("Malformed jwt", e);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Map<String, Object> getDetails(String token) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", getId(token));
        claims.put("email", getEmail(token));
        return claims;
    }

    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
