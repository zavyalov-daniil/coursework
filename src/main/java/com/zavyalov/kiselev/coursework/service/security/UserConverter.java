package com.zavyalov.kiselev.coursework.service.security;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.form.RegisterDto;
import com.zavyalov.kiselev.coursework.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
    public UserView entityToView(UserEntity entity) {
        return new UserView(entity.getId(), entity.getLogin());
    }

    public UserEntity registerDtoToEntity(RegisterDto dto) {
        return new UserEntity(dto.getLogin(), dto.getPassword());
    }
}
