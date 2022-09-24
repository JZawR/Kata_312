package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private Session entityManager;

    public void addRole(Role role) {
        entityManager.saveOrUpdate(role);
    }

    public List<Role> getAllRoles() {
        return entityManager.createQuery("From Role", Role.class).getResultList();
    }

    public List<Role> getRolesByUserId(Long id) {
        return entityManager.createQuery("SELECT r from Role r join r.users u where u.id = :id",
                Role.class).setParameter("id", id).getResultList();
    }
}
