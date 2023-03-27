package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.view.PostView;

import java.util.List;

public class SimplePostService implements IPostService {
    @Override
    public List<PostView> getAllPosts() {
        return null;
    }

    @Override
    public PostView findPostById(Integer postId) {
        return null;
    }

    @Override
    public PostView save(PostForm postForm) {
        return null;
    }

    @Override
    public PostView changeTitle(Integer id, String title) {
        return null;
    }

    @Override
    public PostView changeText(Integer id, String text) {
        return null;
    }

    @Override
    public void delete(Integer postId) {

    }

    @Override
    public void deleteAll() {

    }
}
