package ru.itmentor.spring.boot_security.demo.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_ADMIN ("ROLE_ADMIN"),

    ROLE_USER ("ROLE_USER");

//    ROLE_GUEST ("ROLE_GUEST");

    private final String authority;
    @Override
    public String getAuthority() {
        return authority;
    }

}
