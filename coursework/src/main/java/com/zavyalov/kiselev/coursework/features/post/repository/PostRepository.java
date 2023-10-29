package com.zavyalov.kiselev.coursework.features.post.repository;

import com.zavyalov.kiselev.coursework.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    Optional<PostEntity> findById(Long id);
    void deleteById(Long id);
}
