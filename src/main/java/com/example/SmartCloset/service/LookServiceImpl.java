package com.example.SmartCloset.service;

import com.example.SmartCloset.model.Inclination;
import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.repository.LookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LookServiceImpl implements LookService{

    private LookRepository lookRepository;

    @Autowired
    public LookServiceImpl(LookRepository lookRepository) {
        this.lookRepository = lookRepository;
    }

    @Override
    public Inclination getInclination(String userId) {
        Inclination inclination = new Inclination();
        return inclination;
    }

    @Override
    public Look getLookById(String id) {
        return lookRepository.findById(id).orElse(null);
    }

    @Override
    public Look saveOrUpdate(Look look) {
        return lookRepository.insert(look);
    }

    @Override
    public void delete(String id) {
        lookRepository.deleteById(id);
    }

}
