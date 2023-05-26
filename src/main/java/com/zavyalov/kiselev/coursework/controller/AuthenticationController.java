package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.form.LoginDto;
import com.zavyalov.kiselev.coursework.form.RegisterDto;
import com.zavyalov.kiselev.coursework.repository.UserRepository;
import com.zavyalov.kiselev.coursework.service.security.JWTTokenManager;
import com.zavyalov.kiselev.coursework.service.security.UserConverter;
import com.zavyalov.kiselev.coursework.view.TokenView;
import com.zavyalov.kiselev.coursework.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JWTTokenManager tokenManager;
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JWTTokenManager tokenManager,
                                    UserConverter userConverter,
                                    PasswordEncoder passwordEncoder,
                                    UserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenView login(@RequestBody LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getLogin(),
                        dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenManager.generateToken(authentication);
        return new TokenView(token);
    }

    /*
      todo: Проверка существует ли пользователь с таким же логином
    */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserView register(@RequestBody RegisterDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity entity = userRepository.save(userConverter.registerDtoToEntity(dto));
        return userConverter.entityToView(entity);
    }
}
