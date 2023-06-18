package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.RabbitMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class CustomMessageListener {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);

    @RabbitListener(queues = "mainQueue", containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(final RabbitMqMessage message) {
        log.info("Received message as a generic AMQP 'Message' wrapper: {}", message.toString());
    }
}
