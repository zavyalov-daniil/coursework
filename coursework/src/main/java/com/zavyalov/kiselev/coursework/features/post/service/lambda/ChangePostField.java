package com.zavyalov.kiselev.coursework.features.post.service.lambda;

import com.zavyalov.kiselev.coursework.model.PostNodeEntity;

@FunctionalInterface
public interface ChangePostField {
    PostNodeEntity changeField(String field, PostNodeEntity entity, Object newValue);
}
