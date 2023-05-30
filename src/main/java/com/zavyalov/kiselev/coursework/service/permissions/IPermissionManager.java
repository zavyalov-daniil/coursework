package com.zavyalov.kiselev.coursework.service.permissions;


public interface IPermissionManager {
    Boolean checkPermission(String cmdLabel) throws Exception;
}
