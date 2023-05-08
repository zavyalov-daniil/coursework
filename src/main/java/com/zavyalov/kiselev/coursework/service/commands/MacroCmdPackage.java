package com.zavyalov.kiselev.coursework.service.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MacroCmdPackage implements CommandPackage {
    private List<CommandPackage> packages;

    @Override
    public String getType() {
        return "List<CommandPackage>";
    }
}
