package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.*;
import com.mongodb.lang.Nullable;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Cloth {

    @Id
    private String clothId;

    @NonNull
    private Category category;
    @Nullable
    private String subCategory;
    @Nullable
    private ClothesColor color;

    public Cloth(Category category, String subCategory, ClothesColor color) {
        this.category = category;
        this.subCategory = subCategory;
        this.color = color;
    }
}
