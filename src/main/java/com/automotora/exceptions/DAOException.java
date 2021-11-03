package com.automotora.exceptions;

public class DAOException extends ControllerException {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
