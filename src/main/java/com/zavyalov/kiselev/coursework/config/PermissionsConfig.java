package com.zavyalov.kiselev.coursework.config;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.repository.PermissionRepository;
import com.zavyalov.kiselev.coursework.service.permissions.DefaultPermissionHandler;
import com.zavyalov.kiselev.coursework.service.permissions.IPermissionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

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

    @Bean
    @Scope(value = "singleton")
    @Lazy
    public Map<String, IPermissionHandler> permissionHandlerMap(PermissionRepository permissionRepository) {
        Map<String, IPermissionHandler> map = new HashMap<>();
        for (PermissionEntity entity : permissionRepository.findAll()) {
            try {
                ApplicationContext context = new AnnotationConfigApplicationContext(PermissionHandlersConfig.class);//new AnnotationConfigReactiveWebApplicationContext();
                IPermissionHandler handler = context.getBean(entity.getHandlerName() + "PermissionHandler", IPermissionHandler.class); //Прописать правила нейминга бинов
                map.put(entity.getPermissionName(), handler);
            } catch (Exception ex) {
                map.put(entity.getPermissionName(), getDefaultHandler());
            }
        }

        return map;
    }

    public IPermissionHandler getDefaultHandler() {
        return new DefaultPermissionHandler();
    }
}
