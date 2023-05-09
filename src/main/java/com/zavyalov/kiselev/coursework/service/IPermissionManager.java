package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.service.commands.ICommand;

public interface IPermissionManager {
    ICommand getCommand(String cmdLabel, Object[] arguments);
    Boolean checkPermission(String cmdLabel);
}
