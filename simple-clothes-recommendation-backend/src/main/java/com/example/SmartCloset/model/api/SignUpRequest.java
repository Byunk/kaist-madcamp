package com.example.SmartCloset.model.api;

import lombok.Data;
import lombok.NonNull;

@Data
public class SignUpRequest {
    @NonNull
    private String id;
    @NonNull
    private String pw;
    @NonNull
    private String username;

}
