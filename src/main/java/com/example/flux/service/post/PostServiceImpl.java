package com.example.flux.service.post;

import com.example.flux.entity.Post;
import com.example.flux.entity.User;
import com.example.flux.exception.PostNotFoundException;
import com.example.flux.exception.UserNotFoundException;
import com.example.flux.payload.request.PostRequest;
import com.example.flux.repository.PostRepository;
import com.example.flux.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public Mono createPost(PostRequest request, String userId) {
        User user = userRepository.findAllById(userId)
                .orElseThrow(UserNotFoundException::new);

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    @Override
    public Mono updatePost(PostRequest request, Integer postId) {
        return postRepository.findById(postId)
                .flatMap(post -> {
                    post.updatePost(request.getTitle(), request.getContent());
                    return postRepository.save(post);
                })
                .switchIfEmpty(Mono.error(PostNotFoundException::new));
    }

    @Override
    public Flux getPostList() {
        return postRepository.findAll();
    }

    @Override
    public Mono getPost(Integer postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Mono deletePost(Integer postId) {
        return postRepository.findById(postId)
                .flatMap(post -> {
                    return postRepository.delete(post);
                })
                .switchIfEmpty(Mono.error(PostNotFoundException::new));
    }
}
