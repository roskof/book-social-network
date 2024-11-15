package com.gurkancintesun.book.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class OperationNotPermittedException extends RuntimeException {
    public OperationNotPermittedException(String message) {
        super(message);
    }
}
