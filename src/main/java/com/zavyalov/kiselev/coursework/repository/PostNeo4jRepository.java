package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface PostNeo4jRepository extends Neo4jRepository<PostNodeEntity, Long> {
    /**
     * Возвращает все дочерние посты по id родительского поста
     * Возвращаемые сущности хранят родительские посты. Глубина запроса - 1.
     */
    @Query("""
            MATCH (p:Post) - [r:PARENT_OF] -> (c:Post)
            WHERE ID(p) = $postId
            RETURN c, collect(r), collect(p)""")
    List<PostNodeEntity> findAllChildPosts(@Param("postId") Long postId);

    /**
     * Изменяет текст поста
     * Возвращает изменённую сущность по заданному id или Optional#empty(), если сущность не найдена.
     * Возвращаемая сущность хранит родительский пост. Глубина запроса - 1.
     */
    @Query("""
            MATCH (p:Post) - [r:PARENT_OF] -> (c:Post)
            WHERE ID(c) = $postId
            SET c.text = $newText
            RETURN c, collect(r), collect(p)""")
    Optional<PostNodeEntity> changeText(@Param("postId") Long postId, @Param("newText") String newText);

    /**
     * Изменяет заголовок поста
     * Возвращает изменённую сущность по заданному id или Optional#empty(), если сущность не найдена.
     * Возвращаемая сущность хранит родительский пост. Глубина запроса - 1.
     */
    @Query("""
            MATCH (p:Post) - [r:PARENT_OF] -> (c:Post)
            WHERE ID(c) = $postId
            SET c.title = $newTitle
            RETURN c, collect(r), collect(p)""")
    Optional<PostNodeEntity> changeTitle(@Param("postId") Long postId, @Param("newTitle") String newTitle);
}
