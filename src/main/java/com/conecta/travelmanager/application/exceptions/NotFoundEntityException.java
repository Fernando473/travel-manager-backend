package com.conecta.travelmanager.application.exceptions;

public class NotFoundEntityException extends Exception {
    public NotFoundEntityException() {
        super("Not found entity");
    }

    public NotFoundEntityException(String message) {
        super(message);
    }
}
