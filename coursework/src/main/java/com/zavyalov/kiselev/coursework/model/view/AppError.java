package com.zavyalov.kiselev.coursework.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AppError {
    @NotNull(message="Message cannot be null")
    private String message;
    @NotNull(message="Timestamp cannot be null")
    private LocalDateTime timestamp;

    public AppError(String message) {
        this.message = message;
        timestamp = LocalDateTime.now();
    }
}
