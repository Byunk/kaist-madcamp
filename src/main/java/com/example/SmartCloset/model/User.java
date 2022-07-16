package com.example.SmartCloset.model;

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Optional;

@Document(collection="user")
public class User {

    //private String id;
    //private String pw;
    @Id
    private String userId;

    private String id;
    private String pw;
    private String username;
    @Nullable
    private ArrayList<String> look;
    @Nullable
    private ArrayList<String> liked_look;
    @Nullable
    private ArrayList<String> liked_cloth;
    @Nullable
    private ArrayList<String> following;
    @Nullable
    private Inclination inclination;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getLikedCloth() { return liked_cloth; }

}
