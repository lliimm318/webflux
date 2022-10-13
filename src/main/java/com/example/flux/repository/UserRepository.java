package com.example.flux.repository;

import com.example.flux.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Optional<User> findAllById(String id);

}
