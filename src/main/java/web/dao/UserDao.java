package web.dao;

import web.model.User;

import java.util.List;

/**
 * @author Ali Veliev 08.11.2021
 */

public interface UserDao {
    void createUser(User user);
    User readUser(Long id);
    void updateUser(User user, Long id);
    void deleteUser(Long id);
    User getUserByName(String name);
    List<User> allUsers();
}
