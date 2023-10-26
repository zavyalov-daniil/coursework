package com.zavyalov.kiselev.coursework.features.permission.handlers;

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
