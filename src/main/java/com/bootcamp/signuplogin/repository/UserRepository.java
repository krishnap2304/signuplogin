package com.bootcamp.signuplogin.repository;


import com.bootcamp.signuplogin.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    void deleteAllById(Iterable<? extends String> iterable);
}
