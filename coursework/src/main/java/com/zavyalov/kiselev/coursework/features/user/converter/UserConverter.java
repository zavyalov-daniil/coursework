package com.zavyalov.kiselev.coursework.features.user.converter;

import com.zavyalov.kiselev.coursework.model.PermissionEntity;
import com.zavyalov.kiselev.coursework.model.UserEntity;
import com.zavyalov.kiselev.coursework.features.user.dto.RegisterForm;
import com.zavyalov.kiselev.coursework.features.permission.dto.PermissionView;
import com.zavyalov.kiselev.coursework.features.role.dto.RoleView;
import com.zavyalov.kiselev.coursework.features.user.dto.UserView;
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
