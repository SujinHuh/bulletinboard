package com.eun.common.security.jwt;

import com.eun.common.security.services.UserDetail;
import com.eun.member.vo.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private String JWT_SECRET_KEY = "JWT_SECRET_KEY_LENGTH_LENGTH_LENGTH_JWT_SECRET_KEY_LENGTH_LENGTH_LENGTH";

    private int jwtExpirationMs = 1000 * 60 * 60 * 24;

    public String generateJwtToken(Authentication authentication) {

        UserDetail user = (UserDetail) authentication.getPrincipal();

        Key key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        Key key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Key key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken).getBody().getSubject();
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
