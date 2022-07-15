package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("look")
public class Look {

    @Id
    private String lookId;

    private String tpo;
    private Gender gender;
    private String url;

}

