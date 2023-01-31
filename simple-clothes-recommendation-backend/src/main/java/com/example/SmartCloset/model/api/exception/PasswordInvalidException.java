package com.example.SmartCloset.model.api.exception;

import lombok.Getter;

@Getter
public class PasswordInvalidException extends RuntimeException{

    private ErrorCode errorCode;

    public PasswordInvalidException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}