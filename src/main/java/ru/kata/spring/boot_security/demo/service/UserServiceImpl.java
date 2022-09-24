package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsersWithRoles() {
        return userDao.getAllUsersWithRoles();
    }

    @Transactional
    public void addUser(User newUser) {
        if (newUser.getRoles() == null) {
            newUser.setRoles(roleService.getRoles()
                    .stream().filter(role -> role.getRole().equals("USER")).collect(Collectors.toSet()));
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userDao.addUser(newUser);
    }

    @Transactional
    public void updateUser(User updatedUser) {
        if(!updatedUser.getPassword().equals(userDao.getUserById(updatedUser.getId()).getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userDao.updateUser(updatedUser);
    }

    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteUser(id);
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDao.getUserByNameWithRoles(login);
    }
}
