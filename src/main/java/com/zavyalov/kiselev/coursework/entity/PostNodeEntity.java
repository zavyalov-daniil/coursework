package com.zavyalov.kiselev.coursework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Set;

//Вероятно id не стоит делать изменяемым
@Node("Post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostNodeEntity {
    @Id @GeneratedValue
    private Long id;
    @Property("title")
    private String title;
    @Property("text")
    private String text;
    //@Property("creationTime")
    //@CreatedDate
    //Date creationTime;
    @Relationship(type = "PARENT_OF", direction = Relationship.Direction.INCOMING)
    private Set<PostNodeEntity> parentPost;
    //PostEntity parentPost;
}
