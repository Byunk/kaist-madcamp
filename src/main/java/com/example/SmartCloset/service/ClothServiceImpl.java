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
    public HashMap<ClothesColor, Float> getColorDistribution(ArrayList<Cloth> likedClothes) {
        HashMap<ClothesColor, Float> result = new HashMap<ClothesColor, Float>();
        Long num = clothRepository.count();

        for (ClothesColor color : ClothesColor.values()) {
            Float ratio = (float) clothRepository.countClothByColor(color.getColor()) / num;
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
        return clothRepository.save(cloth);
    }

    public void delete(String id) {
        clothRepository.deleteClothById(id);
    }

}
