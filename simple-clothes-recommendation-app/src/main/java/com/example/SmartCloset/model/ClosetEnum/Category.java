package com.example.SmartCloset.model.ClosetEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Category {
    OUTER("아우터", Arrays.asList("HOOD", "COAT", "JACKET")),
    TOP("상의", Arrays.asList("TSHIRT", "SHIRT", "SWEAT", "NEAT")),
    BOTTOM("하의", Arrays.asList("DENIM", "SHORT", "JOGER", "SLACKS")),
    SHOES("신발", Arrays.asList("SHOES", "SANDLE", "CONVERSE", "HIGHTOP", "SNEAKERS")),
    EMPTY("없음", Collections.EMPTY_LIST);

    private String category;
    private List<String> subCategory;
    Category(String category, List<String> subCategory) {
        this.category = category;
        this.subCategory = subCategory;
    }
    public String getCategory() { return category; }
    public List<String> getSubCategory(Category category) { return category.subCategory; }

    public String getRandomSubCategory() {
        Integer size = this.subCategory.size();
        Random random = new Random();
        return this.subCategory.get(random.nextInt(size));
    }

}