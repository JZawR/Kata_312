package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);
    void updateRole(Role updatedRole, Long id);
    void deleteRole(Long id);
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
