package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ClothServiceImpl implements ClothService {

    private ClothRepository clothRepository;

    @Autowired
    ClothServiceImpl(ClothRepository clothRepository) { this.clothRepository = clothRepository; }

    @Override
    public HashMap<ClothesColor, Integer> getColorDistribution(ArrayList<Cloth> likedClothes) {
        HashMap<ClothesColor, Integer> result = new HashMap<ClothesColor, Integer>();
        Long num = clothRepository.count();

        for (ClothesColor color : ClothesColor.values()) {
            Integer ratio = (int)(clothRepository.countClothByColor(color.getColor()) * 100.0 / num + 0.5);
            result.put(color, ratio);
        }
        return result;
    }

    @Override
    public ArrayList<Cloth> findClothesById(ArrayList<String> ids) {
        ArrayList<Cloth> result = new ArrayList<Cloth>();

        for (String id : ids) {
            result.add(clothRepository.getClothById(id));
        }

        return result;
    }

    @Override
    public Cloth findClothById(String id) {
        return clothRepository.getClothById(id);
    }

    @Override
    public Cloth saveOrUpdate(Cloth cloth) {
        return clothRepository.insert(cloth);
    }

    public void delete(String id) {
        clothRepository.deleteClothById(id);
    }

}
