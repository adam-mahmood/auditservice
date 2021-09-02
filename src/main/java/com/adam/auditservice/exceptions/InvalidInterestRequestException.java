package com.adam.auditservice.exceptions;

public class InvalidInterestRequestException extends RuntimeException {
    public InvalidInterestRequestException() {
    }

    public InvalidInterestRequestException(String message) {
        super(message);
    }

    public InvalidInterestRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
