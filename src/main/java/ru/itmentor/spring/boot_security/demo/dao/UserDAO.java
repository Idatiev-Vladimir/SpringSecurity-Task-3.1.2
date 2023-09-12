package ru.itmentor.spring.boot_security.demo.dao;



import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {

    List<User> allUser();

    User showUser(long id);

    void addUser(User user);

    void updateUser(long id, User user);

    void removeUserById(long id);

    List<Role> getAllRoles();

    User getUserByUsername(String username);

    List<Role> getRoleByUsername(String username);

}
