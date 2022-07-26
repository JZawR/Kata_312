package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User getUserByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles roles where u.login = :name ",
                User.class).setParameter("name", name).getSingleResult();
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("From User", User.class).getResultList();
    }
}
