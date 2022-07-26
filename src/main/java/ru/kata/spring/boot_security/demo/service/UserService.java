package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User getUserById(Long id);
    List<User> getAllUsersWithRoles();
    void addUser(User newUser);
    void updateUser(User updatedUser);
    void deleteUser(Long id);
    Set<Role> getRoles();
    void addRole(Role role);
}
