package aug.web;

import aug.bean.Cart;
import aug.bean.User;
import aug.dao.OrderItemDao;
import aug.dao.impl.OrderItemDaoImpl;
import aug.service.OrderService;
import aug.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: 86134
 * @time: 2021/10/9 20:37
 */
public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();
    
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取UserId
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
    
        System.out.println("OrderServlet程序在【"+ Thread.currentThread().getName() +"】");
        Integer userId = loginUser.getId();
        // 生成订单
        String orderId = orderService.createOrder(cart, userId);
        // 设置域值
        //请求转发
        // req.setAttribute("orderId", orderId);
        // 重定向
        req.getSession().setAttribute("orderId", orderId);
        // 请求转发
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        // 重定向
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
