package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.Category;
import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.Cloth;
import java.util.ArrayList;
import java.util.HashMap;

public interface ClothService {

    ArrayList<Cloth> getClothesById(ArrayList<String> ids);

    Cloth getClothById(String id);

    Cloth saveOrUpdate(Cloth cloth);

    void delete(String id);

}

