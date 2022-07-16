package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Cloth;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

public interface ClothRepository extends MongoRepository<Cloth, String> {

    @CountQuery ("{color: ?0}")
    Integer countClothByColor(String colorName);

    @Query("{id: ?0}")
    Cloth getClothById(String id);

    @DeleteQuery("{id: ?0}")
    void deleteClothById(String id);

}
