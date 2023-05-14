package com.gremio.service;

import com.gremio.service.interfaces.JwtService;
import com.gremio.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret.key}")
    private String jwtSecret;
    @Value("${jwt.token.expiration}")
    private long jwtExpirationInMinutes;
    @Value("${jwt.refresh.expiration}")
    private long refreshExpirationInMinutes;
    private final UserService userService;

    public String extractUsername(final String token) {
       return extractClaim(token, Claims::getSubject);
   }

    public <T> T extractClaim(final String token, final  Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(final UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            final Map<String, Object> extraClaims,
            final UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpirationInMinutes);
    }

    public String generateRefreshToken(
            final UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, jwtExpirationInMinutes);
    }

    public boolean isTokenValid(final String token, final  UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private String buildToken(
            final Map<String, Object> extraClaims,
            final UserDetails userDetails,
            final long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        final byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}