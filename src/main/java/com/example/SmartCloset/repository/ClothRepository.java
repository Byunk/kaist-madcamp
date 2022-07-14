package com.example.SmartCloset.repository;

import com.example.SmartCloset.model.Cloth;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClothRepository extends MongoRepository {

    @CountQuery ("{color: ?0}")
    Integer countClothByColor(String color);

    @Query("{id: ?0}")
    Cloth getClothById(String id);

    @DeleteQuery("{id: ?0}")
    void deleteClothById(String id);

}
