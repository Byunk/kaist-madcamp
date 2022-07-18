package com.example.SmartCloset.model.api;

import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.ClosetEnum.Weather;
import com.mongodb.lang.NonNull;
import com.example.SmartCloset.model.Cloth;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UploadRequest {

    private String id;
    @NonNull
    private Gender gender;
    @NonNull
    private Weather weather;
    @NonNull
    private ArrayList<TPO> tpos;
    @NonNull
    private ArrayList<Cloth> clothes;

    public UploadRequest(String id, Gender gender, Weather weather, ArrayList<TPO> tpos, ArrayList<Cloth> clothes) {
        this.id = id;
        this.gender = gender;
        this.weather = weather;
        this.tpos = tpos;
        this.clothes = clothes;
    }

}
