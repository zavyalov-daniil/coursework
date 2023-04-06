package com.zavyalov.kiselev.coursework.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {
    private String title;
    private String text;
    private Long parentPostId;
}