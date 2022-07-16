package com.example.SmartCloset.model.api;

import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.Cloth;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class UploadRequest {

    private String id;
    private ArrayList<TPO> tpos;
    private Gender gender;
    private ArrayList<Cloth> clothes;

}
