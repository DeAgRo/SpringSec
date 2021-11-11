package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Ali Veliev 08.11.2021
 */

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User readUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User sUser, Long id) {
        User user = entityManager.find(User.class, id);
        user.setName(sUser.getName());
        user.setPassword(sUser.getPassword());
        user.setRoles(sUser.getRoles());
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.name = :name" ,User.class);
        return q.setParameter("name", name).getSingleResult();
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
