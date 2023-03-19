package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.service.IPostService;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    IPostService service;

    public PostController(IPostService service) {
        this.service = service;
    }

    @GetMapping(path = "/new")
    @ResponseStatus(HttpStatus.OK)
    public PostForm getPostForm() {
        return new PostForm();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PostView> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostView getPostById(@PathVariable Integer postId) {
        return service.findPostById(postId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostView createPost(@RequestBody PostForm postForm) {
        return service.save(postForm);
    }

    //@PatchMapping(path = "/{postId}")
    //public ResponseEntity<?> updatePost() {

    //}

    @DeleteMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Integer postId) {
        service.delete(postId);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPosts() {
        service.deleteAll();
    }
}
