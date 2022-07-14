package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

}