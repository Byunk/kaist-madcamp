package com.example.SmartCloset.service;

import com.example.SmartCloset.model.Inclination;
import com.example.SmartCloset.model.Look;

public interface LookService {

    Inclination getInclination(String userId);

    Look getLookById(String id);

    Look saveOrUpdate(Look look);

    void delete(String id);

}
