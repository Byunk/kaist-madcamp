package com.example.SmartCloset.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Optional;

@Document(collection="user")
public class User {

    //private String id;
    //private String pw;
    @Id
    private long userId;

    private String id;
    private String username;
    private Optional<Look[]> look;
    private Optional<Look[]> liked_look;
    private Optional<Cloth[]> liked_cloth;
    private Optional<ObjectId[]> following;
    private Optional<String[]> inclination;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
