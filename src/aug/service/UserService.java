package aug.service;

import aug.bean.User;

public interface UserService {

    /**
     * 注册用户信息
     * @param user 用户信息
     */
    public void registerUser(User user);


    /**
     * 登录账户
     * @param user
     * @return 返回null表示登录不成功，反之登录成功
     */
    public User login(User user);


    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，反之不存在
     */
    public boolean existsUsername(String username);
}
