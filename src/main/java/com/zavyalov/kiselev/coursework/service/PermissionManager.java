package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.config.CommandsConfig;
import com.zavyalov.kiselev.coursework.service.Commands.ICommand;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PermissionManager {
    private Map<String, List<String>> rolesPermissions;
    String role;

    public PermissionManager() {
    }

    /**
     * returns requested command .
     * cmdLabel - name of the command for Factory pattern.
     * args - list of arguments for ICommand implementation constructor. May be empty. Depends on command you want to get
     * <p>
     * ВАЖНО: Если команды не существует, он вернёт null вместо ошибки.
     * TODO: разрешения второго уровня (пользователь-пользователю)
     */
    public ICommand getCommand(String cmdLabel, List<Object> args) {
        ICommand cmd = null;
        if (rolesPermissions.get(role).contains(cmdLabel)) {
            ApplicationContext context = new AnnotationConfigApplicationContext(CommandsConfig.class);//Получает бины из конфига, в котором хранятся конструкторы для команд.
            cmd = (ICommand) (context.getBean(cmdLabel, args));
        }

        return cmd;
    }

}
