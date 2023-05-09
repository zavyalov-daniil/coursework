package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.service.commands.CommandPackage;

public class PostCommandService implements IPostService {
    IPermissionManager permissionManager;

    public PostCommandService(IPermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }
    @Override
    public CommandPackage getPostForm() {
        return permissionManager.getCommand("GetPostFormCmd", new Object[]{}).Execute();
    }

    @Override
    public CommandPackage getAllPosts() {
        return null;
    }

    @Override
    public CommandPackage findPostById(Long postId) {
        return null;
    }

    @Override
    public CommandPackage save(PostForm postForm) {
        return null;
    }

    @Override
    public CommandPackage changeTitle(Long id, String title) {
        return null;
    }

    @Override
    public CommandPackage changeText(Long id, String text) {
        return null;
    }

    @Override
    public CommandPackage delete(Long postId) {
        return null;
    }

    @Override
    public CommandPackage deleteAll() {
        return null;
    }
}
