package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.model.form.LoginForm;
import com.zavyalov.kiselev.coursework.model.form.RegisterForm;
import com.zavyalov.kiselev.coursework.model.form.SetRoleForm;
import com.zavyalov.kiselev.coursework.service.security.AuthenticationService;
import com.zavyalov.kiselev.coursework.service.user.UserService;
import com.zavyalov.kiselev.coursework.model.view.TokenView;
import com.zavyalov.kiselev.coursework.model.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
