package com.example.SmartCloset.model.api.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    
    private ErrorCode errorCode;

    public UserNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
