package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.config.PermissionsConfig;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Scope(value = "singleton")
@NoArgsConstructor
public class PermissionManager implements IPermissionManager {
    public Boolean checkPermission(String cmdLabel) {
        return checkMapRole(cmdLabel) || checkMapUser(cmdLabel);
    }

    /**
     * Checks permissions for roles
     */
    private boolean checkMapRole(String cmdLabel) {

        ApplicationContext permissionsContext = new AnnotationConfigApplicationContext(PermissionsConfig.class);
        Map<String, List<String>> rolesPermissions = (Map<String, List<String>>) permissionsContext.getBean("permissions");
        String role = (String) permissionsContext.getBean("role");

        return rolesPermissions.get(role).contains(cmdLabel);
    }

    /**
     * TODO: checkMapUser
     * Checks personal permissions for users (2-nd levels permissions)
     */
    private boolean checkMapUser(String cmdLabel) {
        return true;
    }

}
