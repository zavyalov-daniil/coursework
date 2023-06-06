package com.zavyalov.kiselev.coursework.service.permissions;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.exception.PermissionNotFoundException;
import com.zavyalov.kiselev.coursework.exception.RoleNotFoundException;
import com.zavyalov.kiselev.coursework.exception.UserNotFoundException;
import com.zavyalov.kiselev.coursework.repository.PermissionRepository;
import com.zavyalov.kiselev.coursework.repository.RoleRepository;
import com.zavyalov.kiselev.coursework.repository.UserRepository;
import com.zavyalov.kiselev.coursework.service.security.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
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
        return checkMapRole(permissionName, username.get()) || checkMapUser(permissionName);

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

        //Проверка на то, что тебе вернули ружное разрешение, вместо null
        if ((currentRolePermissions.contains(permission)) && (permission != null)) {

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
