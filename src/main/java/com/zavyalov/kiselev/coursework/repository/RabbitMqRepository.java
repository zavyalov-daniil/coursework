package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.RabbitMqMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqRepository {
    private final AmqpTemplate amqpTemplate;

    public RabbitMqRepository(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage(/*RabbitMqMessage message*/) {
        RabbitMqMessage message = new RabbitMqMessage("email1", "email2", "sub", "info");
        amqpTemplate.convertAndSend("mainQueue", message.toString());
    }
}
