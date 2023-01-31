package com.example.SmartCloset.model.api;

import com.example.SmartCloset.model.ClosetEnum.Category;
import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.mongodb.lang.Nullable;

import lombok.Data;

@Data
public class SearchClothRequest {
    private Category category;
    @Nullable
    private String subCategory;
    @Nullable
    private ClothesColor color;

    public SearchClothRequest(Category category, String subCategory, ClothesColor color) {
        this.category = category;
        this.subCategory = subCategory;
        this.color = color;
    }
}