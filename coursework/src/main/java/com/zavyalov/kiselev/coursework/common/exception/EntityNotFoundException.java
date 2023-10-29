package com.zavyalov.kiselev.coursework.common.exception;

public class EntityNotFoundException extends RuntimeException {
    private final Object searchParameter;
    private final String modelName;

    public EntityNotFoundException(Object searchParameter, String modelName) {
        this.modelName = modelName;
        this.searchParameter = searchParameter;
    }

    public EntityNotFoundException(String message, Object searchParameter, String modelName) {
        super(message);
        this.modelName = modelName;
        this.searchParameter = searchParameter;
    }

    public EntityNotFoundException(String message, Object searchParameter, String modelName, Throwable cause) {
        super(message, cause);
        this.modelName = modelName;
        this.searchParameter = searchParameter;
    }

    public EntityNotFoundException(Object searchParameter, String modelName, Throwable cause) {
        super(cause);
        this.modelName = modelName;
        this.searchParameter = searchParameter;
    }

    public Object getSearchParameter() {
        return searchParameter;
    }

    public String getModelName() {
        return modelName;
    }
}
