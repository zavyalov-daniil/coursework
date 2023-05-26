package com.zavyalov.kiselev.coursework.view;

import lombok.Data;

@Data
public class TokenView {
    private String token;
    private String tokenType = "Bearer ";

    public TokenView(String token) {
        this.token = token;
    }
}
