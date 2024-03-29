package com.zavyalov.kiselev.coursework.features.role.controller;

import com.zavyalov.kiselev.coursework.features.role.service.RoleService;
import com.zavyalov.kiselev.coursework.features.role.dto.RoleView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/roles")
public class RoleController {

    RoleService roleService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleView> getAllRoles() {
        return roleService.getAllRoles();
    }


}
