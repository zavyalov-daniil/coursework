package com.zavyalov.kiselev.coursework.features.permission;


public interface IPermissionManager {
    Boolean checkPermission(String cmdLabel) throws Exception;
}
