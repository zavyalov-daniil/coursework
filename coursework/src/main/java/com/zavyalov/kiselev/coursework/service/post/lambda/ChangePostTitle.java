package com.zavyalov.kiselev.coursework.service.post.lambda;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;

public class ChangePostTitle implements ChangePostField {
    @Override
    public PostNodeEntity changeField(String field, PostNodeEntity entity, Object newValue) {
        entity.setTitle((String) newValue);
        return entity;
    }
}
