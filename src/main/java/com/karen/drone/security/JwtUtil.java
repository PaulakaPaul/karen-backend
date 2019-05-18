package com.karen.drone.security;

import com.karen.drone.user.models.components.UserRole;
import com.karen.drone.user.models.persistence.UserDAO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    AuthCtx parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            AuthCtx ctx = new AuthCtx();
            ctx.setEmail(body.getSubject());
            ctx.setId(UUID.fromString((String)body.get("userId")));
            ctx.setName((String) body.get("name"));
            ctx.setRole(UserRole.valueOf((String) body.get("role")));

            Date expireDate = new Date((long) body.get("expiresAt"));
            ctx.setExpiresAt(expireDate);

            return ctx;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(UserDAO dao) {
        Claims claims = Jwts.claims().setSubject(dao.getEmail());
        claims.put("userId", dao.getUserId());
        claims.put("name", dao.getName());
        claims.put("role", dao.getRole());

        Date now = new Date();
        Date validUntil = new Date(now.getTime() + validityInMilliseconds);

        claims.put("expiresAt", validUntil);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(validUntil)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
