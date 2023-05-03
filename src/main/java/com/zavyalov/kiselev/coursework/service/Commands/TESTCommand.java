package com.zavyalov.kiselev.coursework.service.Commands;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

public class TESTCommand implements ICommand {
    Object[] arguments;

    public TESTCommand(Object... arguments) {
        this.arguments = arguments;
    }

    @Override
    public void Execute() {
        System.out.println("TEST COMMAND IS WORKING");
        for (Object obj : arguments) {
            System.out.println(obj.toString());
        }
        System.out.println("TEST COMMAND IS FINISHED");
    }
}
