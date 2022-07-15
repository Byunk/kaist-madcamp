package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.ClosetEnum.TPO;

import java.util.EnumSet;
import java.util.HashMap;

public class Inclination {

    // Color Distribution from Clothes
    private HashMap<ClothesColor, Float> colorDistribution;

    // TPO Distribution from Looks
    private HashMap<TPO, Float> tpoDistribution;

}


