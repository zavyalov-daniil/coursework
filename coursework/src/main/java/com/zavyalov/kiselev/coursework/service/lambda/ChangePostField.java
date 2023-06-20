package com.zavyalov.kiselev.coursework.service.lambda;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;

@FunctionalInterface
public interface ChangePostField {
    PostNodeEntity changeField(String field, PostNodeEntity entity, Object newValue);
}
