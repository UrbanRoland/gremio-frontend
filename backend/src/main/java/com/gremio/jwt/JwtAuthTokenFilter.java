package com.gremio.jwt;

import com.gremio.service.interfaces.JwtService;
import com.gremio.service.interfaces.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthTokenFilter extends BasicAuthenticationFilter {
    private static final int CONFLICT_CODE = 409;
    private final JwtService jwtService;
    private final UserService userService;
    public JwtAuthTokenFilter(final AuthenticationManager authManager, final JwtService jwtService, final UserService userService) {
        super(authManager);
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                final HttpServletResponse response,
                final FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            try {
                final String token = header.substring(7);
                final String email = jwtService.extractUsername(token);
                final UserDetails user = loadUserByUsername(email);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

            } catch (final ExpiredJwtException | PreAuthenticatedCredentialsNotFoundException e) {
                response.setStatus(CONFLICT_CODE);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails loadUserByUsername(final String email) throws PreAuthenticatedCredentialsNotFoundException {
        final UserDetails user = userService.loadUserByUsername(email);
        if (user == null) {
            throw new PreAuthenticatedCredentialsNotFoundException("User not found");
        }
        return user;
    }

 }