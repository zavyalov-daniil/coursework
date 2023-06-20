package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.RabbitMqMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqRepository {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqRepository(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(RabbitMqMessage message) {
        rabbitTemplate.convertAndSend("mainQueue", message);
    }
}
