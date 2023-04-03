package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PostEntity;
import com.zavyalov.kiselev.coursework.exception.PostNotFoundException;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.repository.PostRepository;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimplePostService implements IPostService {

    PostRepository postgresRepository;
    PostNeo4jRepository neo4jRepository;
    PostConverter converter;

    public SimplePostService(PostRepository postgresRepository, PostNeo4jRepository neo4jRepository, PostConverter converter) {
        this.postgresRepository = postgresRepository;
        this.neo4jRepository = neo4jRepository;
        this.converter = converter;
    }

    @Override
    public List<PostView> getAllPosts() {
        List<PostEntity> entityList = postgresRepository.findAll();
        List<PostView> res = new ArrayList<>();

        for (PostEntity post : entityList) {
            res.add(converter.postEntityToView(post));
        }

        return res;
    }

    @Override
    public Optional<PostView> findPostById(Integer postId) {
        Optional<PostEntity> res = postgresRepository.findById(postId);
        if (res.isPresent()) {
            return Optional.of(converter.postEntityToView(res.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PostView save(PostForm postForm) {
        PostEntity entity = converter.postFormToEntity(postForm);
        return converter.postEntityToView(postgresRepository.save(entity));
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
        postgresRepository.deleteById(postId);
    }

    @Override
    public void deleteAll() {
        postgresRepository.deleteAll();
    }

    private PostView convertPostEntityToView(PostEntity entity) {
        //TODO: PostView postEntityToView(PostEntity) {}
        return null;
    }

    private PostEntity convertPostFormToEntity(PostForm form) {
        //TODO: PostEntity postFormToEntity(PostForm){}
        return null;
    }

}


