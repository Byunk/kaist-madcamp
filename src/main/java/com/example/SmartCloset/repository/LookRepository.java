package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Look;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LookRepository extends MongoRepository<Look, String> {

    @Query("{tpo:?0}")
    ArrayList<Look> getLookByTpo(String tpo)

    @Query(value = "{tpo:?0}", count = true)
    Integer getLooksCountByTpo(String Tpo);

    @Query("{id:?0}")
    Look getLookByTpo(String id);

}
