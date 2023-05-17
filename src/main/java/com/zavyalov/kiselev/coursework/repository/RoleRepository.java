package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}
