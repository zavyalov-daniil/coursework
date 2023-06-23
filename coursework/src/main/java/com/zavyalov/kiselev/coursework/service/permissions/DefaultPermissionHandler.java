package com.zavyalov.kiselev.coursework.service.permissions;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import lombok.NoArgsConstructor;

/**
 * Checks that role contains required permission;
 */
@NoArgsConstructor
public class DefaultPermissionHandler implements IPermissionHandler {
    @Override
    public Boolean check(Object... args) {
        return true;
    }
}
