package ru.itmentor.spring.boot_security.demo.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUser() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User showUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<Role> getAllRoles() {
        return Arrays.stream(Role.values()).collect(Collectors.toList());
    }

    @Override
    public User getUserByUsername(String username) {
        String hql = "FROM User WHERE username = :username";
        TypedQuery<User> typedQuery = entityManager.createQuery(hql, User.class);
        typedQuery.setParameter("username", username);
        List<User> userList = typedQuery.getResultList();

        if (userList.isEmpty())
            return null;

        return userList.get(0);
    }

    @Override
    public List<Role> getRoleByUsername(String username) {
        String hql = "SELECT roles FROM User " +
                     "JOIN User.roles roles" +
                     " WHERE User.username = :username";
        TypedQuery<Role> typedQuery = entityManager.createQuery(hql, Role.class);
        return typedQuery.setParameter("username", username).getResultList();
    }
}
