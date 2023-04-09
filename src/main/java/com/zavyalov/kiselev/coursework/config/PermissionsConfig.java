package com.zavyalov.kiselev.coursework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class PermissionsConfig {
    @Bean
    @Scope(value = "singleton")
    public Map<String, List<String>> permissions() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> perm1 = new ArrayList<>();
        perm1.add("testCommand");
        map.put("testRole", perm1);
        return map;
    }

    @Bean
    @Scope(value = "singleton")
    public String role() {
        String currentRole = "testRole";
        return currentRole;
    }
}
