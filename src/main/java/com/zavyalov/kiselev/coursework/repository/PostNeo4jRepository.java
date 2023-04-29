package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostNeo4jRepository extends Neo4jRepository<PostNodeEntity, Long> {
    @Query("MATCH (p:Post) - [r:PARENT_OF] -> (c:Post) " +
            "WHERE ID(p)=$postId " +
            "WITH c " +
            "MATCH (c) <- [r:PARENT_OF] - (p:Post) " +
            "RETURN c, collect(r), collect(p) ")
    List<PostNodeEntity> findAllChildPosts(@Param("postId") Long postId);

    @Query("MATCH (p:Post)\n" +
            "WHERE ID(p)=$postId\n" +
            "SET p.text = $newText\n" +
            "RETURN p")
    PostNodeEntity changeText(@Param("postId") Long postId, @Param("newText") String newText);
}
