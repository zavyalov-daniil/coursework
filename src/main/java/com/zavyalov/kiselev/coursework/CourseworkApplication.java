package com.zavyalov.kiselev.coursework;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CourseworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseworkApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(PostNeo4jRepository rep) {
        return args -> {
            rep.deleteAll();
            PostNodeEntity first = new PostNodeEntity(null, "Main", "Main post", new HashSet<>());
            rep.save(first);
        };
    }
}
