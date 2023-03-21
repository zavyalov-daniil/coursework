package com.zavyalov.kiselev.coursework.entity;

import java.util.Date;

public class PostEntity {
    Integer id;
    String title;
    String text;
    Date creationTime;
    PostEntity parentPost;
}
