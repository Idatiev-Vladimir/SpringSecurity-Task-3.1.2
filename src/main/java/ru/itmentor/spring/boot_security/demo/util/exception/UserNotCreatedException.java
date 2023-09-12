package ru.itmentor.spring.boot_security.demo.util.exception;

public class UserNotCreatedException extends RuntimeException{

    public UserNotCreatedException(String message) {
        super(message);
    }
}
