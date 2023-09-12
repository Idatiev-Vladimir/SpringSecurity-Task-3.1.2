package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.UserDAO;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.util.exception.UserNotFoundException;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void timeCreatedUser(User user) {
        user.setCreated_at(LocalDateTime.now());
    }

    public void timeUpdatedUser(User user) {
        user.setUpdated_at(LocalDateTime.now());
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Optional<List<User>> userList = Optional.ofNullable(userDao.allUser());
        return userList.orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public User showUser(long id) {
        Optional<User> user = Optional.ofNullable(userDao.showUser(id));
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        timeCreatedUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);

    }

    @Override
    @Transactional
    public void updateUser(long id, User user) {
        timeUpdatedUser(user);
        userDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        Optional<List<Role>> roles = Optional.ofNullable(userDao.getAllRoles());
        return  roles.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = Optional.ofNullable(userDao.getUserByUsername(username));
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public List<Role> getRoleByUsername(String username) {
        Optional<List<Role>> roles = Optional.ofNullable(userDao.getRoleByUsername(username));
        return roles.orElseThrow(UserNotFoundException::new);
    }

}


