package com.zavyalov.kiselev.coursework.features.permission.handlers;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthorCheckPermissionHandler implements IPermissionHandler {
    @Override
    public Boolean check(Object... args) {
        //TODO: Authority checks
        return true;
    }
}
