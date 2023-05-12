package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.view.PostView;

import java.util.List;
import java.util.Optional;

public class PostCommandService implements IPostService {
    IPermissionManager permissionManager;

    public PostCommandService(IPermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    @Override
    public PostForm getPostForm() {
        return null;
    }

    @Override
    public List<PostView> getAllPosts() {
        return null;
    }

    @Override
    public Optional<PostView> findPostById(Long postId) {
        return Optional.empty();
    }

    @Override
    public PostView save(PostForm postForm) {
        return null;
    }

    @Override
    public Optional<PostView> changeNodeField(Long id, String title) {
        return Optional.empty();
    }

    @Override
    public void delete(Long postId) {

    }

    @Override
    public void deleteAll() {

    }
}
