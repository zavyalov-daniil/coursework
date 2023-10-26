package com.zavyalov.kiselev.coursework.features.user.controller;

import com.zavyalov.kiselev.coursework.features.user.dto.LoginForm;
import com.zavyalov.kiselev.coursework.features.user.dto.RegisterForm;
import com.zavyalov.kiselev.coursework.features.user.dto.SetRoleForm;
import com.zavyalov.kiselev.coursework.features.user.service.AuthenticationService;
import com.zavyalov.kiselev.coursework.features.user.service.UserService;
import com.zavyalov.kiselev.coursework.features.user.dto.TokenView;
import com.zavyalov.kiselev.coursework.features.user.dto.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

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

    @PostMapping("/setRole")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> setRole(@RequestBody SetRoleForm rf) {
        userService.setRole(rf.getUserId(), rf.getRoleId());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<UserView> getAll() {
        return userService.getAll();
    }
}
