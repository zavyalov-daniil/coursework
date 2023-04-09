package com.zavyalov.kiselev.coursework.service.Commands;

public class TESTCommand implements ICommand {
    Object[] arguments;

    public TESTCommand(Object[] args) {
        this.arguments = args;
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
