package com.zavyalov.kiselev.coursework.features.post.converter;

import com.zavyalov.kiselev.coursework.model.PostEntity;
import com.zavyalov.kiselev.coursework.model.PostNodeEntity;
import com.zavyalov.kiselev.coursework.features.post.dto.PostForm;
import com.zavyalov.kiselev.coursework.features.post.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.features.post.dto.PostView;
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
