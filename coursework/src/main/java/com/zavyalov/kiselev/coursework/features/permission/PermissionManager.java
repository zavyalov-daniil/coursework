package com.zavyalov.kiselev.coursework.features.permission;

import com.zavyalov.kiselev.coursework.features.permission.handlers.IPermissionHandler;
import com.zavyalov.kiselev.coursework.model.PermissionEntity;
import com.zavyalov.kiselev.coursework.model.RoleEntity;
import com.zavyalov.kiselev.coursework.model.UserEntity;
import com.zavyalov.kiselev.coursework.common.exception.PermissionNotFoundException;
import com.zavyalov.kiselev.coursework.common.exception.RoleNotFoundException;
import com.zavyalov.kiselev.coursework.common.exception.UserNotFoundException;
import com.zavyalov.kiselev.coursework.features.permission.repository.PermissionRepository;
import com.zavyalov.kiselev.coursework.features.role.repository.RoleRepository;
import com.zavyalov.kiselev.coursework.features.user.repository.UserRepository;
import com.zavyalov.kiselev.coursework.features.user.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Scope(value = "singleton")
@AllArgsConstructor
public class PermissionManager implements IPermissionManager {

    PermissionRepository permissionRepository;
    //    RoleService roleService;
    RoleRepository roleRepository;
    UserRepository userRepository;
    AuthenticationService authenticationService;

    /**
     * Checks first- and second- lvl permissions
     */
    public Boolean checkPermission(String permissionName) throws Exception {
        Optional<String> username = authenticationService.getAuthenticatedUserLogin();
        if (username.isEmpty()) {
            throw new UserNotFoundException();
        }
        //todo return microservice.
        return checkMapRole(permissionName, username.get());// || checkMapUser(permissionName);

    }

    private Map<String, IPermissionHandler> handlers;

    /**
     * Checks permissions for roles
     */
    private boolean checkMapRole(String permissionName, String userName) throws Exception {

        boolean result = false;

        //Взять юзера
        Optional<UserEntity> userEntity = userRepository.findByLogin(userName);
        if (userEntity.isEmpty()) {
            throw new UserNotFoundException();
        }
        //Взять роль

        Optional<RoleEntity> role = roleRepository.findById(userEntity.get().getRole().getRoleId());//roleRepository.findById(1L).get();
        if (role.isEmpty()) {
            throw new RoleNotFoundException();
        }

        //Взять у роли список разрешений
        Set<PermissionEntity> currentRolePermissions = role.get().getPermissionsSet();


        //Чекнуть в списке разрешений нужное
        Optional<PermissionEntity> permission = permissionRepository.findPermissionEntitiesByPermissionName(permissionName);//findPermissionByName(permissionName);
        if (permission.isEmpty()) {
            throw new PermissionNotFoundException();
        }
        boolean contain = false;
        for (PermissionEntity ent : currentRolePermissions) {
            if (ent.equals(permission.get())) {
                contain = true;
            }
        }

        //Проверка на то, что тебе вернули ружное разрешение, вместо null
        if ((contain) && (permission != null)) {

            //Выполнить handler
//            result = permissionRepository.permissionHandler(permission.getHandlerName()).check();//Передавать параметры?
            result = true;

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
