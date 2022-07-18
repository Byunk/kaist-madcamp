package com.example.SmartCloset.model.api;

import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.mongodb.lang.Nullable;
import com.example.SmartCloset.model.Cloth;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UploadRequest {

    private String id;
    @Nullable
    private Gender gender;
    @Nullable
    private ArrayList<TPO> tpos;
    @Nullable
    private ArrayList<Cloth> clothes;

}
