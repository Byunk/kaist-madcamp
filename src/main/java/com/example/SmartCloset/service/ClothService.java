package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.Cloth;

import java.util.HashMap;

public interface ClothService {

    HashMap<String, Integer> getColorDistribution();

    Cloth findClothById(String id);

    Cloth saveOrUpdate(Cloth cloth);

    void delete(String id);

}
