package ru.itmentor.spring.boot_security.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private String username;

    private String password;

    private String fullName;

    private int age;

    private String email;
}
