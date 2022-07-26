package ru.kata.spring.boot_security.demo.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserServiceImpl userService;
    private final RoleDao roleDao;

    public DataInitializer(UserServiceImpl userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    public void run(ApplicationArguments args) {
        User user = new User();
        userService.addRole(new Role("USER"));
        userService.addRole(new Role("ADMIN"));

        user.setLogin("admin");
        user.setPassword("admin");
        user.setName("Ivan");
        user.setSurname("Ivanov");
        user.setAge(11);
        user.setRoles(Set.copyOf(roleDao.getAllRoles()));
        userService.addUser(user);
    }
}
