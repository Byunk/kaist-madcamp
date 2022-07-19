package com.example.SmartCloset.model.api;

import java.util.ArrayList;

import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.ClosetEnum.Weather;

import lombok.Getter;

@Getter
public class EditRequest extends UploadRequest {

    private String lookId;

    public EditRequest(String id, String lookId, Gender gender, Weather weather, ArrayList<TPO> tpos, ArrayList<Cloth> clothes) {
        super(id, gender, weather, tpos, clothes);
        this.lookId = lookId;
    }
    
}
