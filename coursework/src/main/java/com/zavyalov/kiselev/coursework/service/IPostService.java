package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.view.PostView;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    PostForm getPostForm();
    List<PostView> getAllPosts();
    Optional<PostView> findPostById(Long postId);
    PostView save(PostForm postForm);
    Optional<PostView> changeNodeField(Long id, String title);
    void delete(Long postId);
    void deleteAll();
}
