package com.zavyalov.kiselev.coursework.service.permissions;

import com.zavyalov.kiselev.coursework.config.PermissionHandlersConfig;
import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.repository.PermissionRepository;
import com.zavyalov.kiselev.coursework.model.view.PermissionView;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionService {
    final PermissionRepository permissionRepository;

    public List<PermissionView> getAllPermissions() {
        List<PermissionEntity> entList = permissionRepository.findAll();
        List<PermissionView> viewList = new ArrayList<>();
        for (PermissionEntity ent : entList) {
            viewList.add(new PermissionView(ent.getPermissionId(), ent.getPermissionName()));
        }
        return viewList;
    }

    public Optional<PermissionView> getById(Long id) {
        Optional<PermissionEntity> ent = permissionRepository.findById(id);
        if (ent.isEmpty()) {
            return Optional.empty();
        }
        Optional<PermissionView> view = Optional.of(new PermissionView(ent.get().getPermissionId(), ent.get().getPermissionName()));
        return view;
    }

    public PermissionEntity findPermissionByName(String permissionName) {
        Optional<PermissionEntity> opt = permissionRepository.findPermissionEntitiesByPermissionName(permissionName);
        return opt.orElse(null);
    }

    private IPermissionHandler getDefaultHandler() {
        return new DefaultPermissionHandler();
    }

    public IPermissionHandler permissionHandler(String handlerName) {
        IPermissionHandler ph = null;
        ApplicationContext context = new AnnotationConfigApplicationContext(PermissionHandlersConfig.class);//new AnnotationConfigReactiveWebApplicationContext();
        try {
            ph = context.getBean(handlerName + "PermissionHandler", IPermissionHandler.class);
        } catch (Exception ex) {
            ph = getDefaultHandler();
        }
        return ph;
    }
}
