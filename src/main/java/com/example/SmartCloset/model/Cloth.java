package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.*;
import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;

import java.awt.*;

public class Cloth {

    @Id
    private String clothId;

    private Category1 category1;
    private Category2 category2;

    @Nullable
    private TPO tpo;
    private Gender gender;
    private Color color;
}
