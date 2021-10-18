package aug.test;

import aug.bean.User;
import aug.service.UserService;
import aug.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    UserService us = new UserServiceImpl();

    @Test
    public void registerUser() {

        us.registerUser(new User(null,"ToTo","3508539","ToTo@163.com"));
    }

    @Test
    public void login() {
        System.out.println(us.login(new User(null, "ToTo", "3508539", null)));
    }

    @Test
    public void existsUsername() {
        if (us.existsUsername("ToTo")){
            System.out.println("用户名已存在");
        }else System.out.println("用户名可用");
    }
}