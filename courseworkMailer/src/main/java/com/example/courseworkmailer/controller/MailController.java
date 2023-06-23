package com.example.courseworkmailer.controller;

import com.example.courseworkmailer.service.MailForm;
import com.example.courseworkmailer.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {
    private MailService mail;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void postMail(@RequestBody MailForm mailForm) {
        mail.sendSimpleMessage(mailForm.getUserMail(), "noreply@coursework.com", "Coursework Application", mailForm.getMailText());
    }
}
