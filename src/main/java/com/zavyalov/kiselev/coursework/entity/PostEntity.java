package com.zavyalov.kiselev.coursework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    //Date creationTime;
}
