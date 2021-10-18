package aug.web;

import aug.bean.Book;
import aug.bean.Cart;
import aug.bean.CartItem;
import aug.service.BookService;
import aug.service.impl.BookServiceImpl;
import aug.utils.WebUtils;
import com.alibaba.druid.sql.visitor.functions.If;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/26 23:19
 */
public class CartServlet extends BaseServlet {
    
    private BookService bookService = new BookServiceImpl();
    
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 查询图书
        Book book = bookService.queryBook(id);
        // 将图书信息转化成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice());
        // 增加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 查询图书
        Book book = bookService.queryBook(id);
        // 将图书信息转化成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice());
        // 增加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        // 重定向返回目标商品页
        req.getSession().setAttribute("lastName",cartItem.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }
    
    
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 获取当前购物测对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null) {
            // 删除购物车商品项
            cart.deleteItem(id);
            // 重定向回当前操作页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    
    
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null) {
            // 清空购物车
            cart.clear();
            // 重定向回当前操作页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    
    
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            // 修改商品项数量
            cart.updateCount(id, count);
            // 重定向会当前操作页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

}
