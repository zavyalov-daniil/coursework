package com.zavyalov.kiselev.coursework.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostView {
    private Long id;
    private String title;
    private String text;
    private Date creationTime;
}
