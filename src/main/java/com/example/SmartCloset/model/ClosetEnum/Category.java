package com.example.SmartCloset.model.ClosetEnum;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Category1 {
    OUTER("아우터", Arrays.asList("HOOD", "COAT", "JACKET")),
    TOP("상의", Arrays.asList("TSHIRT", "SHIRT", "SWEAT", "NEAT")),
    BOTTOM("하의", Arrays.asList("DENIM", "SHORT", "JOGER")),
    SHOES("신발", Arrays.asList("SHOES", "SANDLE", "CONVERSE", "HIGHTOP")),
    EMPTY("없음", Collections.EMPTY_LIST);

    private String category;
    private List<String> subCategory;
    Category1(String category, List<String> subCategory) {
        this.category = category;
        this.subCategory = subCategory;
    }
    public String getCategory() { return category; }
    public List<String> getSubCategory(Category1 category1) { return }

}