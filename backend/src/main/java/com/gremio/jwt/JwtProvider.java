package com.gremio.jwt;


import com.gremio.model.User;
import com.gremio.repository.UserRepository;
import com.gremio.service.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class); 
    
    @Value("${blog.app.jwtSecret}")
    private String jwtSecret;

    @Value("${blog.app.jwtExpiration}")
    private int jwtExpiration;



    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        final SecretKeySpec secret_key = new SecretKeySpec(this.jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration* 1000L))
		                .signWith(secret_key)
		                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            final SecretKeySpec secret_key = new SecretKeySpec(this.jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
             Jwts.parserBuilder().setSigningKey(secret_key).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            logger.error("Invalid JWT signature ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty", e);
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        final SecretKeySpec secret_key = new SecretKeySpec(this.jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        return Jwts.parserBuilder().setSigningKey(secret_key).build().parseClaimsJws(token).getBody().getSubject();
    }
}