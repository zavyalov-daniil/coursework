package com.zavyalov.kiselev.coursework.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Node("Post")
@NoArgsConstructor
public class PostNodeEntity {
    @Id @GeneratedValue
    @Getter
    private Long id;

    @Setter
    @Getter
    @Property("title")
    private String title;

    @Setter
    @Getter
    @Property("text")
    private String text;

    @Getter
    @Property("creationTime")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationTime;

    @Relationship(type = "PARENT_OF", direction = Relationship.Direction.INCOMING)
    private Set<PostNodeEntity> parentPost;

    public PostNodeEntity(String text, String title, Set<PostNodeEntity> parentPost) {
        this.title = title;
        this.text = text;
        this.parentPost = parentPost;
    }

    public PostNodeEntity getParentPost() {
        Iterator<PostNodeEntity> iterator = parentPost.iterator();
        if(iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }
}
