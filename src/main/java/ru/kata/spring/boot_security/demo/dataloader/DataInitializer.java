package ru.kata.spring.boot_security.demo.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserServiceImpl userService;

    public DataInitializer(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void run(ApplicationArguments args) {
        User user = new User();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role("USER"));
        roleSet.add(new Role("ADMIN"));

        user.setRoles(roleSet);
        user.setLogin("admin");
        user.setPassword("admin");
        user.setName("Ivan");
        user.setSurname("Ivanov");
        user.setAge(11);
        userService.addUser(user);
    }
}
