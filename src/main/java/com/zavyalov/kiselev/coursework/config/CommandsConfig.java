package com.zavyalov.kiselev.coursework.config;

import com.zavyalov.kiselev.coursework.service.Commands.TESTCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommandsConfig {
    @Bean
    @Scope(value = "prototype")
    TESTCommand testCommand(Object[] args) {
        return new TESTCommand(args);
    }
}
