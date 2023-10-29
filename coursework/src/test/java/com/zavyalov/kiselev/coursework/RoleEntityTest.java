package com.zavyalov.kiselev.coursework;

import com.zavyalov.kiselev.coursework.model.PermissionEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RoleEntityTest {
    @Test
    public void equalityTest() {
        PermissionEntity pe1 = new PermissionEntity(12L, "Some_permission_name", "default", null);
        PermissionEntity pe2 = new PermissionEntity(12L, "Some_permission_name", "default", null);
        Assert.assertEquals(pe1, pe2);

    }
}
