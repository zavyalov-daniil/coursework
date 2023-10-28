package com.zavyalov.kiselev.coursework.features.permission.controller;

import com.zavyalov.kiselev.coursework.common.exception.PermissionNotFoundException;
import com.zavyalov.kiselev.coursework.features.permission.service.PermissionService;
import com.zavyalov.kiselev.coursework.features.permission.dto.PermissionView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/permissions")
public class PermissionController {
    PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<PermissionView> getAllPermissions() {
        return service.getAllPermissions();
    }

    @GetMapping(path = "/{permissionId}")
    @ResponseStatus(HttpStatus.OK)
    public PermissionView getPermissionById(@PathVariable Long permissionId) {
        return service.getById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException("Permission with id + " + permissionId + " not found"));
    }
}
