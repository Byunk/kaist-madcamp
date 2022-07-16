package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.*;
import com.mongodb.lang.Nullable;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cloth")
@Data
public class Cloth {

    @Id
    private String clothId;
    //@Nullable
    //private String imgUrl;

    @NonNull
    private Category category;
    @Nullable
    private String subCategory;
    @NonNull
    private Gender gender;
    @Nullable
    private ClothesColor color;
}
