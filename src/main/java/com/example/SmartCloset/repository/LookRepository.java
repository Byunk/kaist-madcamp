package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.Look;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface LookRepository extends MongoRepository<Look, String> {

    @CountQuery("{tpo: ?0}")
    Integer countLookByTPO(String tpoName);

    @Query("{id: ?0}")
    Look getLookById(String id);

}
