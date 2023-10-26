package com.zavyalov.kiselev.coursework.features.user.dto;

import com.zavyalov.kiselev.coursework.features.role.dto.RoleView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private int id;
    private String login;

    private RoleView role;
}
