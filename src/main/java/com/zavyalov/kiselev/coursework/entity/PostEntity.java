package com.zavyalov.kiselev.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity {
    @Id
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "creation_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
}
