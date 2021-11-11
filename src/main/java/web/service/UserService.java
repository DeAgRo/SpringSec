package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

/**
 * @author Ali Veliev 10.11.2021
 */

@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }


    public User readUser(Long id) {
        return userDao.readUser(id);
    }


    public void updateUser(User user, Long id) {
        userDao.updateUser(user, id);
    }


    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }


    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }


    public List<User> allUsers() {
        return userDao.allUsers();
    }
}
