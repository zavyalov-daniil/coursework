package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import com.zavyalov.kiselev.coursework.exception.PostNotFoundException;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.repository.PostNeo4jRepository;
import com.zavyalov.kiselev.coursework.service.lambda.ChangePostField;
import com.zavyalov.kiselev.coursework.service.permissions.PermissionManager;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {
    final PostNeo4jRepository neo4jRepository;
    final PostConverter converter;
    Map<String, ChangePostField> changePostFieldMap;

    final PermissionManager permissionManager;

    public PostService(PostNeo4jRepository neo4jRepository, PostConverter converter,
                       @Qualifier("changePostFieldMap") Map<String, ChangePostField> changePostFieldMap,
                       PermissionManager permissionManager) {
        this.neo4jRepository = neo4jRepository;
        this.converter = converter;
        this.changePostFieldMap = changePostFieldMap;
        this.permissionManager = permissionManager;
    }

    public Optional<PostView> findPostById(Long postId) throws Exception {
        Boolean hasPermission = permissionManager.checkPermission("READ_ALL_NOTES");
        if (hasPermission) {
            Optional<PostNodeEntity> res = neo4jRepository.findById(postId);
            return res.map(entity -> converter.postNodeEntityToView(entity));
        } else {
            throw new AccessDeniedException("User doesn't have the requested permission");
        }
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
