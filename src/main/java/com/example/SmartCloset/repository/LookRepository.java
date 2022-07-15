package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Look;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LookRepository extends MongoRepository<Look, String> {
}
