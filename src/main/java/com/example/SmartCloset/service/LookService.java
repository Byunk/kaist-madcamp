package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.Inclination;
import com.example.SmartCloset.model.Look;

import java.util.ArrayList;
import java.util.HashMap;

public interface LookService {

    HashMap<TPO, Integer> getTPODistribution(ArrayList<Look> likeLooks);

    ArrayList<Look> getLooksByInclination(Inclination inclination);

    ArrayList<Look> getLooksById(ArrayList<String> ids);

    Look getLookById(String id);

    Look saveOrUpdate(Look look);

    void delete(String id);

}
