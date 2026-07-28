package com.gymflow.gymflow.user.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User already exists with this e-mail.");
    }

}