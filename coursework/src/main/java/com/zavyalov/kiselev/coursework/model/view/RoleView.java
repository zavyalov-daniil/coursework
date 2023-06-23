package com.zavyalov.kiselev.coursework.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RoleView {
    private Long roleId;

    private String roleName;

    private Set<PermissionView> permissionSet;
}
