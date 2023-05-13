package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.service.lambda.ChangePostField;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {
    final PostNeo4jRepository neo4jRepository;
    final PostConverter converter;
    Map<String, ChangePostField> changePostFieldMap;

    public PostService(PostNeo4jRepository neo4jRepository, PostConverter converter,
                       @Qualifier("changePostFieldMap") Map<String, ChangePostField> changePostFieldMap) {
        this.neo4jRepository = neo4jRepository;
        this.converter = converter;
        this.changePostFieldMap = changePostFieldMap;
    }

    public PostForm getPostForm() {
        return new PostForm();
    }

    public Optional<PostView> findPostById(Long postId) {
        Optional<PostNodeEntity> res = neo4jRepository.findById(postId);
        return res.map(entity -> converter.postNodeEntityToView(entity));
    }

    public PostView save(PostForm postForm) {
        PostNodeEntity nodeEntity = converter.postFormToNodeEntity(postForm);
        return converter.postNodeEntityToView(neo4jRepository.save(nodeEntity));
    }

    public Optional<PostView> changeNodeField(Long id, Object newValue, String field) {
        Optional<PostNodeEntity> nodeEntityOptional = neo4jRepository.findById(id);

        if (nodeEntityOptional.isPresent()) {
            PostNodeEntity nodeEntity = nodeEntityOptional.get();
            nodeEntity = changePostFieldMap.get(field).changeField(field, nodeEntity, newValue);
            return Optional.of(converter.postNodeEntityToView(neo4jRepository.save(nodeEntity)));
        } else {
            return Optional.empty();
        }
    }

    public void delete(Long postId) {
        Optional<PostNodeEntity> nodeEntityOptional = neo4jRepository.findById(postId);
        if (nodeEntityOptional.isPresent()) {
            PostNodeEntity nodeEntity = nodeEntityOptional.get();
            nodeEntity.setTitle("");
            nodeEntity.setText("");
            neo4jRepository.save(nodeEntity);
        }
    }

    public void deleteAll() {
        neo4jRepository.deleteAll();
    }
}
