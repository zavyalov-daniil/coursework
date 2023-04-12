package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.view.PostView;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostCommandService")
@NoArgsConstructor
public class PostCommandService implements IPostService {


    /* Должен отвечать за контроль полномочий у различных ролей, + менеджерить peer-to-peer разрешения */

    private IQueue queue;

    public PostCommandService(IQueue queue) {
        this.queue = queue;
    }

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
