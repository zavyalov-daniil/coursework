package com.zavyalov.kiselev.coursework.model.view;

import lombok.Data;

/**
 * Используется для передачи токена на фронт.
 * Также передаётся префикс Bearer, который используется для указания типа аутентификации
 * При отправке запросов на сервис этот префикс указывается перед токеном в той же строке
 */
@Data
public class TokenView {
    private String token;
    private String tokenType = "Bearer";

    public TokenView(String token) {
        this.token = token;
    }
}
