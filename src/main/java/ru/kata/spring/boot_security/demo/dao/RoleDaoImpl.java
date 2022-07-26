package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    public void addRole(Role role) {
        entityManager.persist(role);
    }

    public void updateRole(Role updatedRole, Long id) {
        entityManager.merge(updatedRole);
    }

    public void deleteRole(Long id) {
        entityManager.remove(id);
    }

    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    public Role getRoleByName(String name) {
        return entityManager.find(Role.class, name);
    }

    public List<Role> getAllRoles() {
        return entityManager.createQuery("From Role", Role.class).getResultList();
    }
}
