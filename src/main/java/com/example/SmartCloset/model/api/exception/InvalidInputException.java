package com.example.SmartCloset.model.api.exception;

import lombok.Getter;

@Getter
public class InvalidInputException extends RuntimeException{

    private ErrorCode errorCode;

    public InvalidInputException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}