package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PostEntity;
import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.repository.PostRepository;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SimplePostService implements IPostService {

    final PostRepository postgresRepository;
    final PostNeo4jRepository neo4jRepository;
    final PostConverter converter;

    public SimplePostService(PostRepository postgresRepository, PostNeo4jRepository neo4jRepository, PostConverter converter) {
        this.postgresRepository = postgresRepository;
        this.neo4jRepository = neo4jRepository;
        this.converter = converter;
    }

    @Override
    public PostForm getPostForm() {
        return new PostForm();
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
    public Optional<PostView> findPostById(Long postId) {
        Optional<PostEntity> res = postgresRepository.findById(postId);
        return res.map(entity -> converter.postEntityToView(entity));
    }

    @Override
    public PostView save(PostForm postForm) {
        PostNodeEntity nodeEntity = converter.postFormToNodeEntity(postForm);
        nodeEntity = neo4jRepository.save(nodeEntity);
        PostEntity entity = converter.postFormAndNodeToEntity(postForm, nodeEntity);
        return converter.postEntityToView(postgresRepository.save(entity));
    }

    @Override
    public Optional<PostView> changeTitle(Long id, String title) {
        Optional<PostEntity> entityOptional = postgresRepository.findById(id);
        Optional<PostNodeEntity> nodeEntityOptional = neo4jRepository.findById(id);
        if (entityOptional.isPresent() && nodeEntityOptional.isPresent()) {
            PostEntity entity =  entityOptional.get();
            entity.setTitle(title);
            PostNodeEntity nodeEntity = nodeEntityOptional.get();
            nodeEntity.setTitle(title);
            neo4jRepository.save(nodeEntity);
            return Optional.of(converter.postEntityToView(postgresRepository.save(entity)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PostView> changeText(Long id, String text) {
        Optional<PostEntity> entityOptional = postgresRepository.findById(id);
        Optional<PostNodeEntity> nodeEntityOptional = neo4jRepository.findById(id);
        if (entityOptional.isPresent() && nodeEntityOptional.isPresent()) {
            PostEntity entity =  entityOptional.get();
            entity.setText(text);
            PostNodeEntity nodeEntity = nodeEntityOptional.get();
            nodeEntity.setText(text);
            neo4jRepository.save(nodeEntity);
            return Optional.of(converter.postEntityToView(postgresRepository.save(entity)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long postId) {
        Optional<PostNodeEntity> nodeEntityOptional = neo4jRepository.findById(postId);
        if (nodeEntityOptional.isPresent()) {
            PostNodeEntity nodeEntity = nodeEntityOptional.get();
            nodeEntity.setTitle("");
            nodeEntity.setText("");
            neo4jRepository.save(nodeEntity);
        }
        postgresRepository.deleteById(postId);
    }

    @Override
    public void deleteAll() {
        neo4jRepository.deleteAll();
        postgresRepository.deleteAll();
    }
}


