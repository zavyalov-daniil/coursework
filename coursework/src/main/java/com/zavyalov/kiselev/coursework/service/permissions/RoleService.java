package com.zavyalov.kiselev.coursework.service.permissions;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import com.zavyalov.kiselev.coursework.repository.RoleRepository;
import com.zavyalov.kiselev.coursework.model.view.PermissionView;
import com.zavyalov.kiselev.coursework.model.view.RoleView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleService {
    RoleRepository roleRepository;

    public List<RoleView> getAllRoles() {
        List<RoleEntity> rolesEnt = roleRepository.findAll();
        List<RoleView> roleViews = new ArrayList<>(rolesEnt.size());

        for (RoleEntity ent : rolesEnt) {
            Set<PermissionView> pv = new HashSet<>();
            for (PermissionEntity pe : ent.getPermissionsSet()) {
                pv.add(new PermissionView(pe.getPermissionId(), pe.getPermissionName()));
            }
            roleViews.add(new RoleView(ent.getRoleId(), ent.getRoleName(), pv));
        }
        return roleViews;
    }
}
