package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostNeo4jRepository extends Neo4jRepository<PostNodeEntity, Long> {
    @Query("""
            MATCH (p:Post) - [r:PARENT_OF] -> (c:Post)
            WHERE ID(p) = $postId
            WITH c
            MATCH (c) <- [r:PARENT_OF] - (p:Post)
            RETURN c, collect(r), collect(p)""")
    List<PostNodeEntity> findAllChildPosts(@Param("postId") Long postId);
}
