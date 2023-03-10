package com.example.SmartCloset.service;

import com.example.SmartCloset.model.ClosetEnum.Category;
import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.ClosetEnum.Weather;
import com.example.SmartCloset.model.api.SearchClothRequest;
import com.example.SmartCloset.model.api.SearchRequest;
import com.example.SmartCloset.model.api.exception.ErrorCode;
import com.example.SmartCloset.model.api.exception.InvalidInputException;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.Inclination;
import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.repository.LookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class LookServiceImpl implements LookService{

    private LookRepository lookRepository;
    private UserService userService;

    @Autowired
    public LookServiceImpl(LookRepository lookRepository, UserService userService) {
        this.lookRepository = lookRepository;
        this.userService = userService;
    }

    @Override
    public ArrayList<Look> search(SearchRequest request) {
        ArrayList<Look> result = new ArrayList<>();

        for (int i=0; i<request.getNumOutput(); i++) {
            Inclination inclination = new Inclination();

            if (request.getTpos() != null) {
                // if TPO exists, filtering with TPO
                Random random = new Random();
                TPO tpo = request.getTpos().get(random.nextInt(request.getTpos().size()));

                if (request.getClothRequests() != null) {
                    // if User Select Clothes, filtering with Constraints
                    Stream<Look> filteredLooks = lookRepository.findAll().stream();
                    filteredLooks.filter(h -> h.getWeather().equals(Weather.getCurrentWeather()));

                    for (int j = 0; j < request.getClothRequests().size(); j++) {
                        SearchClothRequest clothRequest = request.getClothRequests().get(j);
                        Category category = clothRequest.getCategory();
                        String subCategory = clothRequest.getSubCategory();
                        ClothesColor color = clothRequest.getColor();
                        filteredLooks = filteredLooks.filter(h -> hasClothes(h, category, subCategory, color));
                    }
                    if (filteredLooks.toList().isEmpty()) {
                        throw new EmptyResultDataAccessException("Empty Data Access", 0);
                    }
                    result.add(randomPickLook(filteredLooks.toList()));
                } else {
                    // if User don't select Clothes, filtering with only TPOs
                    
                    result.add(randomPickLook(getLooksByTPOs(request.getTpos()).stream().filter(h -> h.getWeather().equals(Weather.getCurrentWeather())).toList()));
                }
            } else {
                // if not, filtering with Inclination
                User user = userService.getUserById(request.getId());
                if (user.getLikedLook() == null) {
                    result.add(randomPickLook(getAll()));
                } else {
                    ArrayList<Look> likeLooks = (ArrayList<Look>) getLooksById(user.getLikedLook()).stream().filter(h -> h.getWeather().equals(Weather.getCurrentWeather())).toList();
                    inclination.setTpoDistribution(getTPODistribution(likeLooks));
                }
                result.add(getLookByInclination(inclination));
            }
        }
        return result;
    }

    private HashMap<TPO, Float> getTPODistribution(ArrayList<Look> likeLooks) {
        HashMap<TPO, Float> result = new HashMap<>();
        if (likeLooks == null) {
            throw new EmptyResultDataAccessException("Empty Data Access", 0);
        }
        Integer num = likeLooks.size();

        for (TPO tpo : TPO.values()) {
            List<Look> likeLooksOfTpo = likeLooks.stream().filter(look -> look.getTpo().contains(tpo)).toList();
            Float ratio = (float) likeLooksOfTpo.size() / num;
            result.put(tpo, ratio);
        }
        return result;
    }

    // Look??? Constraints??? ????????? Bool??? ??????
    private Boolean hasClothes(Look look, Category category, String subCategory, ClothesColor color) {
        ArrayList<Cloth> clothes = look.getClothes();
        Cloth cloth = clothes.stream().filter(h -> h.getCategory().equals(category)).findFirst().orElse(null);
        if (cloth == null) {
            return false;
        }

        if (cloth.getSubCategory() != null && cloth.getSubCategory() == subCategory) {
            if (cloth.getColor() != null && cloth.getColor() == color) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private Look getLookByInclination(Inclination inclination){
        double random = Math.random();

        double start = 0;
        String resultTPO = null;

        HashMap<TPO, Float> tpoDistribution = inclination.getTpoDistribution();
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

        // TODO: 2022/07/16 Duplication Check

        // TODO: 2022/07/16 Randomly Pick Color Logic
        return looksWithTPO.get((int) random * looksWithTPO.size());
    }

    @Override
    public Look getLookById(String id) {
        Look look = lookRepository.getLookById(id);
        if (look == null) {
            throw new InvalidInputException("No Look with input id", ErrorCode.INVALID_INPUT);
        }
        return look;
    }

    @Override
    public ArrayList<Look> getLooksById(ArrayList<String> ids) {
        ArrayList<Look> result = new ArrayList<Look>();

        if (ids == null) {
            throw new EmptyResultDataAccessException("Empty Data Access", 0);
        }
        for (String id : ids) {
            result.add(lookRepository.getLookById(id));
        }

        return result;
    }

    @Override
    public Look saveOrUpdate(Look look) {
        return lookRepository.save(look);
    }

    @Override
    public void delete(String id) {
        lookRepository.deleteLookById(id);
        User user = userService.findUserWithLookId(id);
        ArrayList<String> uploadLooks = user.getUploadLook();
        if (uploadLooks == null) {
            throw new InvalidInputException("Null UploadLooks", ErrorCode.EMPTY_DATA);
        }
        uploadLooks.remove(id);
        user.setUploadLook(uploadLooks);
        userService.saveOrUpdate(user);
    }

    @Override
    public Look randomPickLook(List<Look> looks) {
        Random random = new Random();
        return looks.get(random.nextInt(looks.size()));   
    }

    @Override
    public List<Look> getAll() {
        return lookRepository.findAll();
    }

    @Override
    public List<Look> getLooksByTPOs(ArrayList<TPO> tpos) {
        List<Look> looks = lookRepository.findAll();
        
        for (TPO tpo : tpos) {
            looks = looks.stream().filter(h -> h.getTpo().contains(tpo)).toList();
        }
        return looks;
    }

    

    

}

