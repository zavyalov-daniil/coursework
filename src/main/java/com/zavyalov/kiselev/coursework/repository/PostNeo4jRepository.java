package com.zavyalov.kiselev.coursework.repository;

import com.zavyalov.kiselev.coursework.entity.PostNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface PostNeo4jRepository extends Neo4jRepository<PostNodeEntity, Long> {

}
