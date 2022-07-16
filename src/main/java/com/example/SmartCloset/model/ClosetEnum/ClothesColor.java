package com.example.SmartCloset.model.ClosetEnum;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;

public enum ClothesColor {

    RED("빨강색"),
    YELLOW("노란색"),
    GREEN("초록색"),
    PURPLE("연보라"),
    BLUE("파랑색"),
    GRAY("회색"),
    SKYBLUE("연청색"),
    WHITE("흰색"),
    BLACK("검은색"),
    NAVY("남색"),
    CREAM("크림색");

    private String color;
    ClothesColor(String color) { this.color = color; }
    public String getColor() { return color; }

    public static ClothesColor getRandomColor() {
        Integer size = ClothesColor.values().length;

        Random random = new Random();
        return ClothesColor.values()[random.nextInt(size)];
    }

}
