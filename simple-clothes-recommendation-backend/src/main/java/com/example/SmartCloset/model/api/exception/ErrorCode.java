package com.example.SmartCloset.model.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    INVALID_INPUT(400,"INPUT-ERR-400","INVALID INPUT"),
    USER_NOT_FOUND(401, "USER-ERR-401", "USER NOT FOUND"),
    PW_INVALID(401, "PW-ERR-401", "PASSWORD INVALID"),
    ID_DUPLICATED(409, "ID-ERR-409", "ID DUPLICATED"),
    EMPTY_DATA(403, "EMPTY-ERR-403", "EMPTY DATA"),
    ;

    private int status;
    private String errorCode;
    private String message;
}