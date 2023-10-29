package com.example.courseworkmailer.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    private JavaMailSender emailSender;

    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
