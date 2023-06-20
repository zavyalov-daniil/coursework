package com.example.courseworkmailer.controller;

import com.example.courseworkmailer.entity.RabbitMqMessage;
import com.example.courseworkmailer.service.MailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


//@EnableRabbit
@Component
public class RabbitMqListener {
    MailService mail;

    //@RabbitListener(queues = "mainQueue")
    RabbitTemplate rabbitTemplate;

    public RabbitMqListener(RabbitTemplate rabbitTemplate, MailService mail) {
        this.mail = mail;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "mainQueue")
    public void listen(RabbitMqMessage message) {
        Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);
        logger.info("Received message: {}", message.toString());
        mail.sendSimpleMessage(message.to(), message.from(), message.subject(), message.text());
    }
}
