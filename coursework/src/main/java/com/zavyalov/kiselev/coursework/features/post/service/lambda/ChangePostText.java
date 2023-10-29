package com.zavyalov.kiselev.coursework.features.post.service.lambda;

import com.zavyalov.kiselev.coursework.model.PostNodeEntity;

public class ChangePostText implements ChangePostField {
    @Override
    public PostNodeEntity changeField(String field, PostNodeEntity entity, Object newValue) {
        entity.setText((String) newValue);
        return entity;
    }
}
