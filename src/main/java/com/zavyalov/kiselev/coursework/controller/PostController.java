package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.exception.PostNotFoundException;
import com.zavyalov.kiselev.coursework.form.PostForm;
import com.zavyalov.kiselev.coursework.service.IPostService;
import com.zavyalov.kiselev.coursework.service.PostService;
import com.zavyalov.kiselev.coursework.service.SimplePostService;
import com.zavyalov.kiselev.coursework.view.PostView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PostView> getAllPosts() throws Exception {
        return service.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostView getPostById(@PathVariable Long postId) throws Exception {
        return service.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostView createPost(@RequestBody PostForm postForm) throws Exception {
        return service.save(postForm);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{postId}/text")
    public PostView updatePostText(@PathVariable Long postId, @RequestBody String text) throws Exception {
        return service.changeNodeField(postId, text, "text").orElseThrow(PostNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{postId}/title")
    public PostView updatePostTitle(@PathVariable Long postId, @RequestBody String title) throws Exception {
        return service.changeNodeField(postId, title, "title").orElseThrow(PostNotFoundException::new);
    }

    @DeleteMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId) throws Exception {
        service.delete(postId);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPosts() throws Exception {
        service.deleteAll();
    }
}
