package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.view.PostView;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<PostView> getAllPosts();
    Optional<PostView> findPostById(Integer postId);
    PostView save(PostForm postForm);
    PostView changeTitle(Integer id, String title);
    PostView changeText(Integer id, String text);
    void delete(Integer postId);
    void deleteAll();
}
