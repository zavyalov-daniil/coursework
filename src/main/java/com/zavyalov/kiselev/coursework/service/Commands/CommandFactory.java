package com.zavyalov.kiselev.coursework.service.Commands;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommandFactory {
    public static ICommand getCommand(String cmdLabel, Object... args) {
        ICommand cmdResult = null;


//        TotalCommander cmder;

        Map<String, TotalCommander> map = fillTheMap();

        cmdResult = map.get(cmdLabel).getCommand(args);

        return cmdResult;
    }

    private static Map<String, TotalCommander> fillTheMap() {
        //TODO: нормальное заполнение мапы с командами
        Map<String, TotalCommander> map = new HashMap<>();
        map.put("testCommand", (Object... arguments) -> {
            return new TESTCommand(arguments);
        });
        map.put("emptyMacroCommand", (Object... arguments) -> {
            return new MacroCommand(new ArrayList<ICommand>());
        });
        return map;
    }

    interface TotalCommander {
        public ICommand getCommand(Object... args);
    }
}
