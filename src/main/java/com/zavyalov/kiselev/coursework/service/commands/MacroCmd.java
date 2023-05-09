package com.zavyalov.kiselev.coursework.service.commands;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MacroCmd implements ICommand {
    List<ICommand> list;

    public MacroCmd(List<ICommand> cmds) {
        this.list = new ArrayList<>(cmds);
    }

    public MacroCmd() {
        this.list = new ArrayList<>();
    }

    @Override
    public CommandPackage Execute() {
        ArrayList<CommandPackage> packages = new ArrayList<>();
        for (ICommand cmd : list) {
            packages.add(cmd.Execute());
        }
        return new MacroCmdPackage(packages);
    }
}
