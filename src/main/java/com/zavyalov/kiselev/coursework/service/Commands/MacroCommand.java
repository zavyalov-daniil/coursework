package com.zavyalov.kiselev.coursework.service.Commands;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MacroCommand implements ICommand {
    List<ICommand> list;

    public MacroCommand(List<ICommand> cmds) {
        this.list = new ArrayList<>(cmds);
    }

    public MacroCommand() {
        this.list = new ArrayList<>();
    }

    @Override
    public void Execute() {
        for (ICommand cmd : list) {
            cmd.Execute();
        }
    }
}
