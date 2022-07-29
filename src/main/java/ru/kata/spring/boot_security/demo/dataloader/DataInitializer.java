package ru.kata.spring.boot_security.demo.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserServiceImpl userService;
    private final RoleService roleService;

    public DataInitializer(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public void run(ApplicationArguments args) {
        User user = new User();
        roleService.add(new Role("USER"));
        roleService.add(new Role("ADMIN"));

        user.setRoles(roleService.getRoles());
        user.setLogin("admin");
        user.setPassword("admin");
        user.setName("Ivan");
        user.setSurname("Ivanov");
        user.setAge(11);
        userService.addUser(user);
    }
}
