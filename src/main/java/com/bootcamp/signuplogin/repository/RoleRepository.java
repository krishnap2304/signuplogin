package com.bootcamp.signuplogin.repository;

import com.bootcamp.signuplogin.model.Role;
import com.bootcamp.signuplogin.model.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(RoleEnum name);

    void deleteAllById(Iterable<? extends String> iterable);
}
