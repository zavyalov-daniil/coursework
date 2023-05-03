package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.exception.PostNotFoundException;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.service.IPostService;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
        return service.getPostForm();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PostView> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostView getPostById(@PathVariable Long postId) throws PostNotFoundException {
        return service.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostView createPost(@RequestBody PostForm postForm) {
        return service.save(postForm);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{postId}/text")
    public PostView updatePostText(@PathVariable Long postId, @RequestBody String text) throws PostNotFoundException {
        return service.changeText(postId, text).orElseThrow(PostNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{postId}/title")
    public PostView updatePostTitle(@PathVariable Long postId, @RequestBody String title) throws PostNotFoundException {
        return service.changeTitle(postId, title).orElseThrow(PostNotFoundException::new);
    }

    @DeleteMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId) {
        service.delete(postId);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPosts() {
        service.deleteAll();
    }
}
