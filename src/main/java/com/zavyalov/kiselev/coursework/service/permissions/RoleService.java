package com.zavyalov.kiselev.coursework.service.permissions;

import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import com.zavyalov.kiselev.coursework.repository.RoleRepository;
import com.zavyalov.kiselev.coursework.view.RoleView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    RoleRepository roleRepository;

    public List<RoleView> getAllRoles() {
        List<RoleEntity> rolesEnt = roleRepository.findAll();
        List<RoleView> roleViews = new ArrayList<>(rolesEnt.size());
        for (RoleEntity ent : rolesEnt) {
            roleViews.add(new RoleView(ent.getRoleId(), ent.getRoleName()));
        }
        return roleViews;
    }
}
