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


    HashMap<Tpo, Float> getcolorDistribution(){
            return colorDistribution;
    }

    HashMap<Tpo, Float> getTPODistribution(){
            return tpoDistribution;
    }

    void setTPODistribution(HashMap<Tpo, Float> tpoDistribution){
            this.tpoDistribution = tpoDistribution;
    }

    void setcolorDistribution(HashMap<ClothesColor, Float> colorDistribution){
            this.colorDistribution = colorDistribution;
    }

}


