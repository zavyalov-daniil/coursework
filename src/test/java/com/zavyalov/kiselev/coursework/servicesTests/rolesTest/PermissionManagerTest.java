package com.zavyalov.kiselev.coursework.servicesTests.rolesTest;

import com.zavyalov.kiselev.coursework.service.Commands.ICommand;
import com.zavyalov.kiselev.coursework.service.PermissionManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PermissionManagerTest {
    @Test
    public void noRoleTest() {

    }

    @Test
    public void noPermissionTest() {
        PermissionManager pm = new PermissionManager();
        ICommand cmd = pm.getCommand("unExistedCommand", null);
        Assert.assertNull(cmd);
    }

    @Test
    public void getCommandTest() {
        PermissionManager pm = new PermissionManager();
        ICommand cmd = pm.getCommand("testCommand", null);
        Assert.assertNotNull(cmd);
    }
}
