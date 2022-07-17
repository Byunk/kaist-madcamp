package com.example.SmartCloset.service;

import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ClothServiceImpl implements ClothService {

    private ClothRepository clothRepository;

    @Autowired
    ClothServiceImpl(ClothRepository clothRepository) { this.clothRepository = clothRepository; }

    @Override
    public ArrayList<Cloth> getClothesById(ArrayList<String> ids) {
        ArrayList<Cloth> result = new ArrayList<Cloth>();

        for (String id : ids) {
            result.add(clothRepository.getClothById(id));
        }

        return result;
    }

    @Override
    public Cloth getClothById(String id) {
        return clothRepository.getClothById(id);
    }

    @Override
    public Cloth saveOrUpdate(Cloth cloth) {
        return clothRepository.save(cloth);
    }

    @Override
    public void delete(String id) {
        clothRepository.deleteClothById(id);
    }

}

