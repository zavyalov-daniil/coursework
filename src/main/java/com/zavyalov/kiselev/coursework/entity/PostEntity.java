package com.zavyalov.kiselev.coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class PostEntity {
    @Id
    Long id;
    String title;
    String text;
    //Date creationTime;
}
