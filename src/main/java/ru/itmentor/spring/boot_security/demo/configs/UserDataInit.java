package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.dao.UserDAO;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class UserDataInit {
    private final UserDAO userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataInit(UserDAO userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        User admin = new User("admin", passwordEncoder.encode("admin"));
        User user = new User("user", passwordEncoder.encode("user"));

        admin.setRoles(Set.of(Role.ROLE_ADMIN));
        user.setRoles(Set.of(Role.ROLE_USER));

        userDao.addUser(admin);
        userDao.addUser(user);

    }
}
