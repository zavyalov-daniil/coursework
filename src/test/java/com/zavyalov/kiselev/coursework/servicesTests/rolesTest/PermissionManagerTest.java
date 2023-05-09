package com.zavyalov.kiselev.coursework.servicesTests.rolesTest;

import com.zavyalov.kiselev.coursework.config.CommandsConfig;
import com.zavyalov.kiselev.coursework.service.commands.CommandFactory;
import com.zavyalov.kiselev.coursework.service.commands.ICommand;
import com.zavyalov.kiselev.coursework.service.commands.TESTCmd;
import com.zavyalov.kiselev.coursework.service.PermissionManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class PermissionManagerTest {

    @Test
    public void noPermissionTest() {
        PermissionManager pm = new PermissionManager();
        ICommand cmd = pm.getCommand("unExistedCommand", null);
        Assert.assertNull(cmd);
    }

    @Test
    public void getCommandTest() {
        PermissionManager pm = new PermissionManager();
        List<String> str = new ArrayList<String>();
        str.add("one");
        str.add("two");
        ICommand cmd = pm.getCommand("testCommand", (new ArrayList<Object>(str)).toArray());
        Assert.assertNotNull(cmd);
    }

    @Test
    public void configTest() {
        ICommand cmd = null;
        ApplicationContext context = new AnnotationConfigApplicationContext(CommandsConfig.class);//Получает бины из конфига, в котором хранятся конструкторы для команд.
        List<String> str = new ArrayList<String>();
        str.add("one");
        str.add("two");
        cmd = (ICommand) context.getBean("testCMD", "aaa", "bbb");
        cmd.Execute();
        Assert.assertNotNull(cmd);
    }

    @Test
    public void commandFactoryTest() {
        ICommand cmd1 = null;
        cmd1 = CommandFactory.getCommand("testCommand", "one", "two", 42);
        ICommand cmd2 = null;
        cmd2 = CommandFactory.getCommand("emptyMacroCommand");

        Assert.assertNotNull(cmd1);
        Assert.assertNotNull(cmd2);

    }

    @Test
    public void lambdaTest() {
        interface TotalCommander {
            public ICommand getCommand(Object... args);
        }
        TotalCommander cmder;
        cmder = (Object... args) -> {
            return new TESTCmd(args);
        };
        ICommand cmd = cmder.getCommand("one", "two");
        cmd.Execute();
    }
}
