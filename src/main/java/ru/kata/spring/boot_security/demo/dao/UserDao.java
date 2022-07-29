package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User updatedUser);
    void deleteUser(Long id);
    User getUserById(Long id);
    User getUserByNameWithRoles(String name);
    List<User> getAllUsersWithRoles();
}
