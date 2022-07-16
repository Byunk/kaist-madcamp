package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{id: ?0}")
    Optional<User> findByLoginId(String id);
}