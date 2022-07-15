package com.example.SmartCloset.model;

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    private String[] look;
    @Nullable
    private String[] liked_look;
    @Nullable
    private String[] liked_cloth;
    @Nullable
    private String[] following;
    @Nullable
    private Inclination inclination;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
