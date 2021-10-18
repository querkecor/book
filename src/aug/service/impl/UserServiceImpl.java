package aug.service.impl;

import aug.bean.User;
import aug.dao.UserDao;
import aug.dao.impl.UserDaoImpl;
import aug.service.UserService;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/1 23:19
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUsernamePassword(user.getUsername(),user.getPassword());

    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUsername(username) != null;
    }
}
