package com.gurkancintesun.book.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum BusinessErrorCodes {
    UNKNOWN_ERROR(-1,INTERNAL_SERVER_ERROR,"Internal error, contact the admin"),
    NO_CODE(0, NOT_IMPLEMENTED, "No code."),
    INCORRECT_CURRENT_PASSWORD(300,BAD_REQUEST,"Current password is incorrect."),
    NEW_PASSWORD_DOES_NOT_MATCHED(301,BAD_REQUEST,"The new password does not match."),
    ACCOUNT_LOCKED(302,FORBIDDEN,"User account is locked."),
    ACCOUNT_DISABLED(303,FORBIDDEN,"User account is disabled."),
    BAD_CREDENTIALS(304,FORBIDDEN,"Username and / or password is incorrect."),
    ;
    private final int code;
    private final HttpStatus httpStatus;
    private final String description;

}
