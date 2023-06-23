package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.entity.RabbitMqMessage;
import com.zavyalov.kiselev.coursework.repository.RabbitMqRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mail")
@AllArgsConstructor
public class CallMailController {
    RabbitMqRepository rep;

    @ResponseStatus(HttpStatus.OK)
    public void postMail() {
        rep.sendMessage(new RabbitMqMessage("dezavv1@gmail.com", "dabikis2855@gmail.com", "CourseWork", "Hello there"));
    }
}