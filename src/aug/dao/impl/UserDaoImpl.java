package aug.dao.impl;

import aug.bean.User;
import aug.dao.UserDao;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/1 22:15
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return query(User.class,sql,username);
    }

    @Override
    public User queryUsernamePassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return query(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`)values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
