package com.zavyalov.kiselev.coursework.service.security;

import com.zavyalov.kiselev.coursework.form.LoginForm;
import com.zavyalov.kiselev.coursework.view.TokenView;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JWTTokenManager tokenManager;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 JWTTokenManager tokenManager) {

        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    public TokenView loginUser(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getLogin(),
                        loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenManager.generateToken(authentication);
        return new TokenView(token);
    }

    public Optional<String> getAuthenticatedUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                return Optional.of(userDetails.getUsername());
            }
        }
        return Optional.empty();
    }
}
