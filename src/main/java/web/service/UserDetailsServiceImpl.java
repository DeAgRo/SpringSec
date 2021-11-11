package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import org.springframework.security.core.userdetails.User;

/**
 * @author Ali Veliev 08.11.2021
 */

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        web.model.User user = userDao.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        } else {
            return new User(user.getName(),user.getPassword(),user.getAuthorities());
        }
    }
}
