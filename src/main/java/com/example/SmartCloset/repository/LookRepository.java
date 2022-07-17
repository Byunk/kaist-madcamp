package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Look;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookRepository extends MongoRepository<Look, String> {

    @Query("{id: ?0}")
    Look getLookById(String id);

    @Query("{tpo: ?0}")
    List<Look> getLookByTpo(String tpo);

    @CountQuery(value = "{tpo: ?0}")
    Integer countLookByTPO(String Tpo);

}
