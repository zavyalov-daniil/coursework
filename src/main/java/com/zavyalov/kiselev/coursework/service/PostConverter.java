package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PostEntity;
import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PostConverter {
    public PostView postEntityToView(PostEntity entity) {
        return new PostView(entity.getId(), entity.getTitle(), entity.getText());
    }

    public PostNodeEntity postFormToNodeEntity(PostForm form) {
        return new PostNodeEntity(null, form.getTitle(), form.getText(), new HashSet<PostNodeEntity>());
    }

    public PostEntity postFormAndNodeToEntity(PostForm postForm, PostNodeEntity postNodeEntity) {
        return new PostEntity(postNodeEntity.getId(), postForm.getTitle(), postForm.getText());
    }
}
