package com.zavyalov.kiselev.coursework.features.user.filter;

import com.zavyalov.kiselev.coursework.features.user.CustomUserDetailsService;
import com.zavyalov.kiselev.coursework.features.user.JWTTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private JWTTokenManager tokenManager;
    private CustomUserDetailsService customUserDetailsService;

    public JWTAuthenticationFilter(JWTTokenManager tokenManager, CustomUserDetailsService customUserDetailsService) {
        this.tokenManager = tokenManager;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getJWTFromRequest(request);
        if(StringUtils.hasText(token) && tokenManager.validateToken(token)) {
            String username = tokenManager.getUsernameFromJWT(token);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
