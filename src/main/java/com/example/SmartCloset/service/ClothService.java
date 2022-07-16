package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.Cloth;
import java.util.ArrayList;
import java.util.HashMap;

public interface ClothService {

    HashMap<ClothesColor, Float> getColorDistribution(ArrayList<Cloth> likedClothes);

    // TODO: 2022/07/15 Category2 에 대한 distribution

    ArrayList<Cloth> findClothesById(ArrayList<String> ids);

    Cloth findClothById(String id);

    Cloth saveOrUpdate(Cloth cloth);

    void delete(String id);

}
