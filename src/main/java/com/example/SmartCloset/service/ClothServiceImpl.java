package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ClothServiceImpl implements ClothService {

    private ClothRepository clothRepository;

    @Autowired
    ClothServiceImpl(ClothRepository clothRepository) { this.clothRepository = clothRepository; }

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
