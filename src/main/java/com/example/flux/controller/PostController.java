package com.example.flux.controller;

import com.example.flux.payload.request.PostRequest;
import com.example.flux.service.post.PostService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public Mono createPost(@NotNull @RequestHeader("Authorization") String auth,
                           @RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest, auth);
    }

    @PutMapping("/{postId}")
    public Mono updatePost(@RequestBody PostRequest postRequest,
                           @PathVariable Integer postId) {
        return postService.updatePost(postRequest, postId);
    }

    @GetMapping
    public Flux getPostList() {
        return postService.getPostList();
    }

    @GetMapping("/{postId}")
    public Mono getPost(@PathVariable Integer postId) {
        return postService.getPost(postId);
    }

    @DeleteMapping("/{postId}")
    public Mono deletePost(@PathVariable Integer postId) {
        return postService.deletePost(postId);
    }

}
