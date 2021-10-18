package aug.web;

import aug.bean.User;
import aug.service.UserService;
import aug.service.impl.UserServiceImpl;
import aug.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/19 16:26
 */
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();
    private UserService us = new UserServiceImpl();
    
    protected void ajaxUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
        
        
        
        // 登出操作
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁session
        req.getSession().invalidate();
        // 重定向
        resp.sendRedirect(req.getContextPath());
    }
    
    // 登录方法
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String username = req.getParameter("username");
        // String password = req.getParameter("password");
        User user = WebUtils.paramToBean(req.getParameterMap(),new User());
        User login = us.login(new User(null, user.getUsername(), user.getPassword(), null));
        if (login == null){
            //错误信息和回显信息的表单项信息保存的request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());

            //逃回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //登录成功，跳转到成功页面
            req.getSession().setAttribute("user", login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
    
    // 注册方法
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        // String username = req.getParameter("username");
        // String password = req.getParameter("password");
        // String email = req.getParameter("email");
        // 获取Session中的验证码
        String token =(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        
        String code = req.getParameter("code");

        User user = WebUtils.paramToBean(req.getParameterMap(),new User());

        //2、检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.existsUsername(user.getUsername())) {

                //把回显信息保存到request域中
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("password",user.getPassword());
                req.setAttribute("email",user.getEmail());

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registerUser(new User(null,user.getUsername(),user.getPassword(),user.getEmail()));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        } else {
            //把回显信息保存到request域中
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());
            req.setAttribute("email",user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
