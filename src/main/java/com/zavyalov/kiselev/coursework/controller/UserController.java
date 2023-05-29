package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.form.LoginForm;
import com.zavyalov.kiselev.coursework.form.RegisterForm;
import com.zavyalov.kiselev.coursework.repository.UserRepository;
import com.zavyalov.kiselev.coursework.service.security.AuthenticationService;
import com.zavyalov.kiselev.coursework.service.security.JWTTokenManager;
import com.zavyalov.kiselev.coursework.service.security.UserConverter;
import com.zavyalov.kiselev.coursework.service.security.UserService;
import com.zavyalov.kiselev.coursework.view.TokenView;
import com.zavyalov.kiselev.coursework.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private AuthenticationService authenticationService;
    private UserService userService;

    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenView login(@RequestBody LoginForm loginForm) {
        return authenticationService.loginUser(loginForm);
    }

    /*
      todo: Проверка существует ли пользователь с таким же логином
    */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserView register(@RequestBody RegisterForm registerForm) {
        return userService.registerNewUser(registerForm);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String heh() {
        return authenticationService.getAuthenticatedUserLogin();
    }
}
