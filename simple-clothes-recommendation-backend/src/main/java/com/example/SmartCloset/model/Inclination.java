package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.mongodb.lang.Nullable;

import lombok.Data;

import java.util.EnumSet;
import java.util.HashMap;

@Data
public class Inclination {

    // Color Distribution from Clothes
    //private HashMap<ClothesColor, Float> colorDistribution;

    // TPO Distribution from Looks
    @Nullable
    private HashMap<TPO, Float> tpoDistribution;

}



