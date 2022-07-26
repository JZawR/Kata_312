package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsersWithRoles() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void addUser(User newUser) {
        System.out.println(newUser.getRoles());
        if (newUser.getRoles() == null) {
            newUser.setRoles(roleDao.getAllRoles().stream().limit(1).collect(Collectors.toSet()));
        }
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userDao.addUser(newUser);
    }

    @Transactional
    public void updateUser(User updatedUser) {
        userDao.updateUser(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDao.getUserByName(login);
    }

    public Set<Role> getRoles() {
        return Set.copyOf(roleDao.getAllRoles());
    }

    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}
