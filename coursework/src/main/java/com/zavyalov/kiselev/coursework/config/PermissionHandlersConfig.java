package com.zavyalov.kiselev.coursework.config;

import com.zavyalov.kiselev.coursework.service.permissions.AuthorCheckPermissionHandler;
import com.zavyalov.kiselev.coursework.service.permissions.DefaultPermissionHandler;
import com.zavyalov.kiselev.coursework.service.permissions.IPermissionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Naming rule: <handler_name_from_db>PermissionHandler. Example: for author_check handler bean name looks like author_checkPermissionHandler
 */
@Configuration
public class PermissionHandlersConfig {

    @Bean
    @Scope("prototype")
    @Lazy
    public IPermissionHandler defaultPermissionHandler() {
        return new DefaultPermissionHandler();
    }

    @Bean
    @Scope("prototype")
    @Lazy
    public IPermissionHandler author_checkPermissionHandler() {
        return new AuthorCheckPermissionHandler();
    }
}
