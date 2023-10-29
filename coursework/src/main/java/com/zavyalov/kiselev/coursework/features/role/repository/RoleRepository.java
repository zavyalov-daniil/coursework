package com.zavyalov.kiselev.coursework.features.role.repository;

import com.zavyalov.kiselev.coursework.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
