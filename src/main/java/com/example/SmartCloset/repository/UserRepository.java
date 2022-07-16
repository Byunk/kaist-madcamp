package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}