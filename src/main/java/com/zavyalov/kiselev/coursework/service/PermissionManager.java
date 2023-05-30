package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import com.zavyalov.kiselev.coursework.repository.PermissionRepository;
import com.zavyalov.kiselev.coursework.repository.RoleRepository;
import com.zavyalov.kiselev.coursework.service.permissions.IPermissionHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Scope(value = "singleton")
@NoArgsConstructor
@AllArgsConstructor
public class PermissionManager implements IPermissionManager {

    PermissionService permissionService;
    //    RoleService roleService;
    RoleRepository roleRepository;

    /**
     * Checks first- and second- lvl permissions
     */
    public Boolean checkPermission(String permissionName) throws Exception {
        //todo return microservice.
        return checkMapRole(permissionName) || checkMapUser(permissionName);
    }

    private Map<String, IPermissionHandler> handlers;

    /**
     * Checks permissions for roles
     */
    private boolean checkMapRole(String permissionName) throws Exception {

        boolean result = false;


        //Взять роль
        RoleEntity role = roleRepository.findById(1L).get();
        //(RoleEntity) permissionsContext.getBean("role");//Не подходит, нужна Entity, получать через IRole

        //Взять у роли список разрешений
        Set<PermissionEntity> currentRolePermissions = role.getPermissionsSet();

        //Чекнуть в списке разрешений нужное
        PermissionEntity permission = permissionService.findPermissionByName(permissionName);

        //Проверка на то, что тебе вернули ружное разрешение, вместо null
        if ((currentRolePermissions.contains(permission)) && (permission != null)) {

            //Выполнить handler
            result = permissionService.permissionHandler(permission.getHandlerName()).check();//Передавать параметры?

        } else {
            return false;
        }

        return result;
    }

    /**
     * TODO: check second lvl permissions
     * Checks personal permissions for users (2-nd levels permissions)
     */
    private boolean checkMapUser(String permissionName) {
        return true;
    }

}
