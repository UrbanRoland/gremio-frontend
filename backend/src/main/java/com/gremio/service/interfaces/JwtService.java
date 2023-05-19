package com.gremio.service.interfaces;

import com.gremio.model.dto.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;
public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    AuthResponse refreshAuthToken(String token);
    String generateRefreshToken(UserDetails userDetails);
}