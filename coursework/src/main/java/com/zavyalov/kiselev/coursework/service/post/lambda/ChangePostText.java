package com.zavyalov.kiselev.coursework.service.post.lambda;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;

public class ChangePostText implements ChangePostField {
    @Override
    public PostNodeEntity changeField(String field, PostNodeEntity entity, Object newValue) {
        entity.setText((String) newValue);
        return entity;
    }
}
