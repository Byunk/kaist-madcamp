package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.Category;
import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.api.SearchRequest;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.Inclination;
import com.example.SmartCloset.model.Look;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface LookService {

    // Search Look with Constraints
    ArrayList<Look> search(SearchRequest request);

    Look randomPickLook(List<Look> looks);

    ArrayList<Look> getLooksById(ArrayList<String> ids);

    List<Look> getAll();

    List<Look> getLooksByTPOs(ArrayList<TPO> tpos);

    Look getLookById(String id);

    Look saveOrUpdate(Look look);

    void delete(String id);

}
