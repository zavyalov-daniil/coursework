package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.RabbitMqMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class RabbitMqRepository {
    private final RabbitTemplate rabbitTemplate;

    @Value("${coursework.rabbitmq.default-queue}")
    private String defaultQueue;

    public RabbitMqRepository(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(RabbitMqMessage message) {
        rabbitTemplate.convertAndSend(defaultQueue, message);
    }
}
