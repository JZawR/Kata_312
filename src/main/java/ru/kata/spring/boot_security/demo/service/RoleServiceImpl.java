package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public void add(Role role) {
        roleDao.add(role);
    }

    public Set<Role> getRoles() {
        return Set.copyOf(roleDao.getAllRoles());
    }

    @Override
    public Set<Role> getRolesByUserId(Long id) {
        return Set.copyOf(roleDao.getRolesByUserId(id));
    }
}
