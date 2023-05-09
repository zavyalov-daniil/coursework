package com.zavyalov.kiselev.coursework.service.commands;

public class TESTCmd implements ICommand {
    Object[] arguments;

    public TESTCmd(Object... arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandPackage Execute() {
        System.out.println("TEST COMMAND IS WORKING");
        for (Object obj : arguments) {
            System.out.println(obj.toString());
        }
        System.out.println("TEST COMMAND IS FINISHED");
        return new TESTCmdPackage("TESTCmdPackage");
    }
}
