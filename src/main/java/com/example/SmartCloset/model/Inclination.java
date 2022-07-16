package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.ClosetEnum.TPO;

import java.util.EnumSet;
import java.util.HashMap;

public class Inclination {

    // Color Distribution from Clothes
    private HashMap<ClothesColor, Integer> colorDistribution;

    // TPO Distribution from Looks
    private HashMap<TPO, Integer> tpoDistribution;

    public void setColorDistribution(HashMap<ClothesColor, Integer> colorDistribution) {
        this.colorDistribution = colorDistribution;
    }

    public void setTpoDistribution(HashMap<TPO, Integer> tpoDistribution) {
        this.tpoDistribution = tpoDistribution;
    }
}


