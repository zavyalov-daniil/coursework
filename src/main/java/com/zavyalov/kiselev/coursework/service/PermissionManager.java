package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.config.PermissionsConfig;
import com.zavyalov.kiselev.coursework.service.permissions.IPermissionHandler;
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

    /**
     * Checks first- and second- lvl permissions
     */
    public Boolean checkPermission(String permissionName) {
        return checkMapRole(permissionName) || checkMapUser(permissionName);
    }

    private Map<String, IPermissionHandler> handlers;

    /**
     * Checks permissions for roles
     */
    private boolean checkMapRole(String permissionName) {
        boolean result = false;
        ApplicationContext permissionsContext = new AnnotationConfigApplicationContext(PermissionsConfig.class);
        handlers = (Map<String, IPermissionHandler>) permissionsContext.getBean("permissionHandlerMap");
        String role = (String) permissionsContext.getBean("role");//Не подходит, нужна Entity
        //TODO TODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODOTODO
        return result;
    }

    /**
     * TODO: checkMapUser
     * Checks personal permissions for users (2-nd levels permissions)
     */
    private boolean checkMapUser(String permissionName) {
        return true;
    }

}
