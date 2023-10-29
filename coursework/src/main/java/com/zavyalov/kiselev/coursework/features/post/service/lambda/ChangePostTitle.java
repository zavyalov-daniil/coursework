package com.zavyalov.kiselev.coursework.features.post.service.lambda;

import com.zavyalov.kiselev.coursework.model.PostNodeEntity;

public class ChangePostTitle implements ChangePostField {
    @Override
    public PostNodeEntity changeField(String field, PostNodeEntity entity, Object newValue) {
        entity.setTitle((String) newValue);
        return entity;
    }
}
