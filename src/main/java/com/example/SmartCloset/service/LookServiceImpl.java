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
    public HashMap<TPO, Float> getTPODistribution(ArrayList<Look> likeLooks) {
        HashMap<TPO, Float> result = new HashMap<TPO, Float>();
        Long num = lookRepository.count();

        for (TPO tpo : TPO.values()) {
            Float ratio = (float) lookRepository.countLookByTPO(tpo.getTpo()) / num;
            result.put(tpo, ratio);
        }
        return result;
    }

    @Override
    public ArrayList<Look> getLooksByInclination(Inclination inclination, int count){
        ArrayList<Look> result = new ArrayList<Look>();

        double random = Math.random();

        for (int i = 0; i < count; i++) {
            // Randomly Pick TPO
            double start = 0;
            String resultTPO = null;

            HashMap<TPO, Float> tpoDistribution = inclination.getTPODistribution();
            for (TPO tpo: TPO.values()) {
                if (random > start && random < start + tpoDistribution.get(tpo)) {
                    resultTPO = tpo.getTpo();
                    break;
                } else {
                    start += tpoDistribution.get(tpo);
                }
            }

            // Pick Look according to Randomly Picked TPO
            List<Look> looksWithTPO = lookRepository.getLookByTpo(resultTPO);
            result.add(looksWithTPO.get((int) random * looksWithTPO.size()));
            // TODO: 2022/07/16 Duplication Check

            // TODO: 2022/07/16 Randomly Pick Color Logic
        }
        return result;
    }
    
    @Override
    public Look getLookById(String id) {
        return lookRepository.findById(id).orElse(null);
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
    public Look saveOrUpdate(Look look) {
        return lookRepository.insert(look);
    }

    @Override
    public void delete(String id) {
        lookRepository.deleteById(id);
    }

}
