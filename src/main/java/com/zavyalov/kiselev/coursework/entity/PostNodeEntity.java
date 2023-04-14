package com.zavyalov.kiselev.coursework.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Set;

@Node("Post")
@Getter
@NoArgsConstructor
public class PostNodeEntity {
    @Id @GeneratedValue
    private Long id;

    @Setter
    @Property("title")
    private String title;

    @Setter
    @Property("text")
    private String text;

    @Property("creationTime")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    Date creationTime;

    @Setter
    @Relationship(type = "PARENT_OF", direction = Relationship.Direction.INCOMING)
    private Set<PostNodeEntity> parentPost;

    public PostNodeEntity(String text, String title, Set<PostNodeEntity> parentPost) {
        this.title = title;
        this.text = text;
        this.parentPost = parentPost;
    }
}
