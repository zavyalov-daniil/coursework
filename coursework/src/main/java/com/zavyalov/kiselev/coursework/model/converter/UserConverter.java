package com.zavyalov.kiselev.coursework.model.converter;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.model.form.RegisterForm;
import com.zavyalov.kiselev.coursework.model.view.PermissionView;
import com.zavyalov.kiselev.coursework.model.view.RoleView;
import com.zavyalov.kiselev.coursework.model.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class UserConverter {
    public UserView entityToView(UserEntity entity) {
        if (entity.getRole() != null) {
            Set<PermissionView> pv = new HashSet<>();
            for (PermissionEntity pe : entity.getRole().getPermissionsSet()) {
                pv.add(new PermissionView(pe.getPermissionId(), pe.getPermissionName()));
            }
            return new UserView(entity.getId(), entity.getLogin(), new RoleView(entity.getRole().getRoleId(), entity.getRole().getRoleName(), pv));
        } else {
            return new UserView(entity.getId(), entity.getLogin(), null);
        }
    }

    public UserEntity registerDtoToEntity(RegisterForm dto) {
        return new UserEntity(dto.getLogin(), dto.getPassword());
    }
}