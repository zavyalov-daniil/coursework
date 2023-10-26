package com.zavyalov.kiselev.coursework.features.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank()
    @Size(min = 5, max = 50)
    private String login;

    @NotBlank
    @Size(min = 5, max = 50)
    private String password;
}
