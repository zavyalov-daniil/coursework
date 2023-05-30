package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByLogin(String username);

    Boolean existsByLogin(String username);
}
