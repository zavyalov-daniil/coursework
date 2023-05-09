package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.service.commands.CommandPackage;
import com.zavyalov.kiselev.coursework.view.PostView;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    CommandPackage getPostForm();
    CommandPackage getAllPosts();
    CommandPackage findPostById(Long postId);
    CommandPackage save(PostForm postForm);
    CommandPackage changeTitle(Long id, String title);
    CommandPackage changeText(Long id, String text);
    CommandPackage delete(Long postId);
    CommandPackage deleteAll();
}
