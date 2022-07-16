package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.mongodb.lang.Nullable;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("look")
@Data
public class Look {

    @Id
    private String lookId;
    @Nullable
    private String imgUrl;

    //private String tpo;
    @NonNull
    private ArrayList<TPO> tpo;
    @NonNull
    private Gender gender;
    @NonNull
    private ArrayList<String> clothes;

}

