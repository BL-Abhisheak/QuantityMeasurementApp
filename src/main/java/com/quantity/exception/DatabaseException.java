package com.quantity.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}