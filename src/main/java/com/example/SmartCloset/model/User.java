package com.example.SmartCloset.model;

import com.mongodb.lang.Nullable;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection="user")
@Data
public class User {

    //private String id;
    //private String pw;
    @Id
    private String userId;

    @NonNull @Indexed(unique = true)
    private String id;
    @NonNull
    private String pw;
    @NonNull
    private String name;

    @Nullable
    private ArrayList<String> uploadLook;
    @Nullable
    private ArrayList<String> likedLook;
    @Nullable
    private ArrayList<String> likedUser;

}
