package com.server.exception.custom;

public class DataOperationException extends RuntimeException {

    public DataOperationException(String message) {
        super(message);
    }

    public DataOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
