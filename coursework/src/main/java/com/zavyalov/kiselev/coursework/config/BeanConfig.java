package com.zavyalov.kiselev.coursework.config;

import com.zavyalov.kiselev.coursework.service.post.lambda.ChangePostField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:config.properties")
public class BeanConfig {
    @Value("#{${change.post.lambdas}}")
    HashMap<String, String> changePostLambdas;
    @Bean
    public Map<String, ChangePostField> changePostFieldMap() {
        HashMap<String, ChangePostField> changePostFieldMap = new HashMap<>();
        changePostLambdas.forEach(
                (k, v) -> {
                    Object lambdaObject;
                    try {
                        lambdaObject = Class.forName(v).getConstructor().newInstance();
                    } catch (InstantiationException | ClassNotFoundException | InvocationTargetException |
                             IllegalAccessException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    ChangePostField lambda = (ChangePostField) lambdaObject;
                    changePostFieldMap.put(k, lambda);
                }
        );
        return changePostFieldMap;
    }

}
