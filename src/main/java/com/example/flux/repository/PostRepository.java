package com.example.flux.repository;

import com.example.flux.entity.Post;
import com.example.flux.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveCrudRepository<Post, Integer> {

    Flux findByUserOrderByDate(User user);

}
