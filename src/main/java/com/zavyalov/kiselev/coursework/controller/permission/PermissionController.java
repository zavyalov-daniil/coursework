package com.zavyalov.kiselev.coursework.controller.permission;

import com.zavyalov.kiselev.coursework.service.PermissionService;
import com.zavyalov.kiselev.coursework.service.PostService;
import com.zavyalov.kiselev.coursework.service.SimplePostService;
import com.zavyalov.kiselev.coursework.view.PermissionView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<PermissionView> getPermissionById(@PathVariable Long permissionId) {
        Optional<PermissionView> opt = service.getById(permissionId);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(opt);
    }
}
