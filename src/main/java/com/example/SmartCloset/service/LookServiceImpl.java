package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.Inclination;
import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.repository.LookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LookServiceImpl implements LookService{

    private LookRepository lookRepository;

    @Autowired
    public LookServiceImpl(LookRepository lookRepository) {
        this.lookRepository = lookRepository;
    }

    @Override
    public HashMap<TPO, Integer> getTPODistribution(ArrayList<Look> likeLooks) {
        HashMap<TPO, Integer> result = new HashMap<TPO, Integer>();
        Long num = lookRepository.count();

        for (TPO tpo : TPO.values()) {
            Integer ratio = (int)(lookRepository.countLookByTPO(tpo.getTpo()) * 100.0 / num + 0.5);
            result.put(tpo, ratio);
        }
        return result;
    }

    @Override
    public ArrayList<Look> getLooksByInclination(Inclination inclination) {
        List<Look> result = lookRepository.findAll();

        //확률 계산

        //랜덤으로 뽑기

        return null;
    }

    @Override
    public ArrayList<Look> getLooksById(ArrayList<String> ids) {
        ArrayList<Look> result = new ArrayList<Look>();

        for (String id : ids) {
            result.add(lookRepository.getLookById(id));
        }

        return result;
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
