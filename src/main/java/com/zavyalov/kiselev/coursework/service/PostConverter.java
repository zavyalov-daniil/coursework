package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PostEntity;
import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.view.PostView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@AllArgsConstructor
public class PostConverter {
    private PostNeo4jRepository neo4jRepository;

    public PostView postEntityToView(PostEntity entity) {
        return new PostView(entity.getId(), entity.getTitle(), entity.getText(), entity.getCreationTime());
    }

    public PostView postNodeEntityToView(PostNodeEntity entity) {
        return new PostView(entity.getId(), entity.getTitle(), entity.getText(), entity.getCreationTime());
    }

    public PostNodeEntity postFormToNodeEntity(PostForm form) {
        HashSet<PostNodeEntity> parent = new HashSet<>();
        neo4jRepository.findById(form.getParentPostId()).ifPresent(parent::add);
        return new PostNodeEntity(form.getText(), form.getTitle(), parent);
    }

    public PostEntity postFormAndNodeToEntity(PostForm postForm, PostNodeEntity postNodeEntity) {
        return new PostEntity(postNodeEntity.getId(), postForm.getTitle(), postForm.getText(), postNodeEntity.getCreationTime());
    }
}
