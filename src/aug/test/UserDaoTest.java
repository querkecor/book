package aug.test;

import aug.bean.User;
import aug.dao.UserDao;
import aug.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUsername() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUsername("admin") == null) {
            System.out.println("用户名可用");
        } else System.out.println("用户名已存在");
    }

    @Test
    public void queryUsernamePassword() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUsernamePassword("admin", "admin") == null){
            System.out.println("用户名或密码错误");
        }else System.out.println("登录成功");
    }

    @Test
    public void saveUser() {
        String sql = "insert into t_user(`username`,`password`,`email`)values(?,?,?)";
        UserDao userDao = new UserDaoImpl();
        if(userDao.saveUser(new User(null,"多事之秋","3508539","duoshi@163.com")) != -1){
            System.out.println("注册成功");
        }else System.out.println("注册失败");
    }
}