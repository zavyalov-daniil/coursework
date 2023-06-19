package com.zavyalov.kiselev.coursework.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavyalov.kiselev.coursework.entity.RabbitMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableRabbit
public class CustomMessageListener {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);
    RabbitTemplate rabbitTemplate;

    public CustomMessageListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "mainQueue")
    public void receiveMessage(RabbitMqMessage message) throws IOException {
        log.info("Received message: {}", message.toString());
    }
}
