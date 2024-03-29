package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping
public class UserController {
    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String toLogin() {
        return "redirect:/login";
    }

    @GetMapping("admin/users")
    public String showAllUsers(Model model) {
        model.addAttribute("people", userService.getAllUsersWithRoles());
        return "users";
    }

    @GetMapping("admin/user/{id}")
    public String showUserForAdmin(@PathVariable("id") Long id, Model userModel) {
        userModel.addAttribute("man", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/user/{login}")
    public String showUserForUser (Model userModel, Principal principal) {
        userModel.addAttribute("man", userService.loadUserByUsername(principal.getName()));
        return "user";
    }

    @GetMapping("/new")
    public String showNewUserPage(@ModelAttribute("userBoy") User user) {
        return "new";
    }

    @PostMapping("admin/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:users";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateUserPage(@PathVariable Long id, Model userModel) {
        userModel.addAttribute("userUpdate", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        user.setRoles(roleService.getRolesByUserId(id));
        userService.updateUser(user);
        return "redirect:admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:admin/users";
    }
}
