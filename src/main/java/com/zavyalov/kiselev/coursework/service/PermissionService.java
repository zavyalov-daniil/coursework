package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PermissionEntity;
import com.zavyalov.kiselev.coursework.repository.PermissionRepository;
import com.zavyalov.kiselev.coursework.view.PermissionView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionService {
    final PermissionRepository repository;

    public List<PermissionView> getAllPermissions() {
        List<PermissionEntity> entList = repository.findAll();
        List<PermissionView> viewList = new ArrayList<>();
        for (PermissionEntity ent : entList) {
            viewList.add(new PermissionView(ent.getId(), ent.getPermissionName()));
        }
        return viewList;
    }

    public Optional<PermissionView> getById(Long id) {
        Optional<PermissionEntity> ent = repository.findById(id);
        if (ent.isEmpty()) {
            return Optional.empty();
        }
        Optional<PermissionView> view = Optional.of(new PermissionView(ent.get().getId(), ent.get().getPermissionName()));
        return view;
    }
}
