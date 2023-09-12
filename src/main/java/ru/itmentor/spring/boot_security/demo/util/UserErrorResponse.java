package ru.itmentor.spring.boot_security.demo.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class UserErrorResponse {

    private String message;

    private Long timestamp;

}
