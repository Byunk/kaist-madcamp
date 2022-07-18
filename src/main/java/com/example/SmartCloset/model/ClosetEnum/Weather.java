package com.example.SmartCloset.model.ClosetEnum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public enum Weather {

    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;
    
    public static Weather getCurrentWeather() {
        Calendar cal = Calendar.getInstance();
        Integer month = cal.get(Calendar.MONTH);

        switch (month) {
            case 1, 2, 3: return SPRING;
            case 4, 5, 6: return SUMMER;
            case 7, 8, 9: return AUTUMN;
            case 10, 11, 0: return WINTER;
            default: return null;
        }
    }

    public static Weather getRandomWeather() {
        Random random = new Random();
        ArrayList<Weather> weathers = new ArrayList<>();
        weathers.add(Weather.SPRING);
        weathers.add(Weather.SUMMER);
        weathers.add(Weather.AUTUMN);
        weathers.add(Weather.WINTER);

        return weathers.get(random.nextInt(weathers.size()));
    }
    
}
