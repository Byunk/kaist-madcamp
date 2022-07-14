package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Look;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LookRepository extends MongoRepository<Look, String> {
}
