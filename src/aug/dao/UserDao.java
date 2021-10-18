package aug.dao;

import aug.bean.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回null说明用户名不存在
     */
    public User queryUsername(String username);


    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null，说明用户名或密码错误
     */
    public User queryUsernamePassword(String username, String password);


    /**
     * 保存用户信息
     * @param user
     * @return 放回-1表示保存用户信息失败，反之为sql语句影响的行数
     */
    public int saveUser(User user);
}
