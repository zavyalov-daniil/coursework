package com.zavyalov.kiselev.coursework;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.service.IPostService;
import com.zavyalov.kiselev.coursework.service.SimplePostService;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;

@SpringBootApplication
@EnableNeo4jAuditing
public class CourseworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseworkApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(SimplePostService service, PostNeo4jRepository repository) {
        return args -> {
            //Создание тестовых постов
            service.deleteAll();
            PostForm first = new PostForm("Main", "Main post", -1L);
            PostForm p2 = new PostForm("p2", " dfpost", service.save(first).getId());
            PostForm p3 = new PostForm("p3", " dfpost", service.save(p2).getId());
            Long parId = service.save(p3).getId();
            PostForm p4 = new PostForm("p4", " fdpost", parId);
            PostForm p5 = new PostForm("p5", "sd post", parId);
            service.save(p4);
            service.save(p5);
        };
    }
}
