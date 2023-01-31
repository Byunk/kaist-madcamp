package com.example.SmartCloset.model.api.exception;

import lombok.Getter;

@Getter
public class IdDuplicatedException extends RuntimeException{

    private ErrorCode errorCode;

    public IdDuplicatedException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}