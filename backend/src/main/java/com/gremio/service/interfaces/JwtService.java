package com.gremio.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
}