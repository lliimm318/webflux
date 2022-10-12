package com.example.flux.service.post;

import com.example.flux.payload.request.PostRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Mono createPost(PostRequest request, String userId);

    Mono updatePost(PostRequest request, Integer postId);

    Flux getPostList();

    Mono getPost(Integer postId);

    Mono deletePost(Integer postId);

}
