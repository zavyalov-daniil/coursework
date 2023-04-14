package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.config.CommandsConfig;
import com.zavyalov.kiselev.coursework.config.PermissionsConfig;
import com.zavyalov.kiselev.coursework.service.Commands.ICommand;
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
public class PermissionManager {
    /**
     * returns requested command .
     * cmdLabel - name of the command for Factory pattern.
     * arguments - array of arguments for ICommand implementation constructor. May be empty. Depends on command you want to get
     * <p>
     * ВАЖНО: Если команды не существует, он вернёт null вместо ошибки.
     * TODO: разрешения второго уровня (пользователь-пользователю)
     */
    public ICommand getCommand(String cmdLabel, Object[] arguments) {

        ICommand cmd = null;
//        if () {
        if (checkPermission(cmdLabel)) {
            ApplicationContext context = new AnnotationConfigApplicationContext(CommandsConfig.class);//Получает бины из конфига, в котором хранятся конструкторы для команд.
            cmd = (ICommand) (context.getBean(cmdLabel, arguments));
        }

        return cmd;
    }

    
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
