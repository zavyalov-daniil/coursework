package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.form.LoginForm;
import com.zavyalov.kiselev.coursework.form.RegisterForm;
import com.zavyalov.kiselev.coursework.service.security.AuthenticationService;
import com.zavyalov.kiselev.coursework.service.security.UserService;
import com.zavyalov.kiselev.coursework.view.TokenView;
import com.zavyalov.kiselev.coursework.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public TokenView login(@Valid @RequestBody LoginForm loginForm) {
        return authenticationService.loginUser(loginForm);
    }

    /*
      todo: Проверка существует ли пользователь с таким же логином
      todo: Проверить работу валидации
    */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserView register(@Valid @RequestBody RegisterForm registerForm, BindingResult result) {
        return userService.registerNewUser(registerForm);
    }
}
