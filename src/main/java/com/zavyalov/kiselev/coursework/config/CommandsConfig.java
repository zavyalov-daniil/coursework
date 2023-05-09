package com.zavyalov.kiselev.coursework.config;

import com.zavyalov.kiselev.coursework.service.commands.TESTCmd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommandsConfig {
    @Bean
    @Scope(value = "prototype")
    TESTCmd testCommand(Object[] args) {
        return new TESTCmd(args);
    }
}
