package com.zavyalov.kiselev.coursework.features.permission.repository;

import com.zavyalov.kiselev.coursework.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findPermissionEntitiesByPermissionName(String permissionName);
}
