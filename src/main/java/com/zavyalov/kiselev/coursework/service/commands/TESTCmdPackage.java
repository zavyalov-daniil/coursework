package com.zavyalov.kiselev.coursework.service.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TESTCmdPackage implements CommandPackage {
    private String result;
    @Override
    public String getType() {
        return "String";
    }
}
