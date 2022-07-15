package com.example.SmartCloset.model.ClosetEnum;

public enum Category1 {
    OUTER("아우터"),
    TOP("상의"),
    BOTTOM("하의"),
    SHOES("신발");

    private String category;
    Category1(String category) { this.category = category; }
    public String getCategory() { return category; }

}