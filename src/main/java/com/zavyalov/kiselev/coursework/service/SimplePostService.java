package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PostEntity;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.apache.catalina.startup.ClassLoaderFactory;

import java.util.ArrayList;
import java.util.List;

public class SimplePostService implements IPostService {

    PostRepository postRepository;
    NeoRepository neoRepository;

    public SimplePostService(PostRepository postRepo, NeoRepository neoRepository) {
        this.postRepository = postRepo;
        this.neoRepository = neoRepository;
    }

    @Override
    public List<PostView> getAllPosts() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostView> res = new ArrayList<PostView>();

        for (PostEntity post : entityList) {
            res.add(postEntityToView(post));
        }

        return res;
    }

    @Override
    public PostView findPostById(Integer postId) {

        PostView res = postEntityToView(postRepository.findByID(postID));
        return res;

    }

    @Override
    public PostView save(PostForm postForm) {
        PostEntity entity = postFormToEntity(postForm);
        PostView view = postEntityToView(entity);
        return view;
    }

    @Override
    public PostView changeTitle(Integer id, String title) {
        // todo ???
        return null;
    }

    @Override
    public PostView changeText(Integer id, String text) {
        // todo ???
        return null;
    }

    @Override
    public void delete(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void deleteAll() {
        postRepository.deteleAll();
    }

    private PostView postEntityToView(PostEntity entity) {
        //TODO: PostView postEntityToView(PostEntity) {}
        return null;
    }

    private PostEntity postFormToEntity(PostForm form) {
        //TODO: PostEntity postFormToEntity(PostForm){}
        return null;
    }

}


