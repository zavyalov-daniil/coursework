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

    public PostEntity postFormToEntity(PostForm form) {
        return new PostEntity(null, form.getTitle(), form.getText());
    }

    public PostNodeEntity postFormToNodeEntity(PostForm form) {
        return new PostNodeEntity(null, form.getTitle(), form.getText(), new HashSet<PostNodeEntity>());
    }
}
