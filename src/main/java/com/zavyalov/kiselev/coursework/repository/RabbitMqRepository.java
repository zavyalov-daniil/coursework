package com.zavyalov.kiselev.coursework.repository;

import org.springframework.amqp.core.AmqpTemplate;

public class RabbitMqRepository {
    private final AmqpTemplate amqpTemplate;

    public RabbitMqRepository(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(String message) {
        amqpTemplate.convertAndSend("mainQueue", message);
    }
}
