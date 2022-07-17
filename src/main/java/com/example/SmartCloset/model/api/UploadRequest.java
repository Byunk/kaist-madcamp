package com.example.SmartCloset.model.api;

import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.Cloth;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UploadRequest {

    private String id;
    private Gender gender;
    private ArrayList<TPO> tpos;
    private ArrayList<Cloth> clothes;

}
