package com.zavyalov.kiselev.coursework.features.user.repository;

import com.zavyalov.kiselev.coursework.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByLogin(String username);

    Boolean existsByLogin(String username);
}
