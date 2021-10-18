package aug.web;

import aug.bean.Book;
import aug.bean.Page;
import aug.service.BookService;
import aug.service.impl.BookServiceImpl;
import aug.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/21 13:13
 */
public class ClientBookServlet extends BookServlet{
    private BookService bookService = new BookServiceImpl();
    /**
     * 处理分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2 调用bookService.page(pageNo,pageSize)方法
        Page<Book> page = bookService.page(pageNo, pageSize);
        // 新增：设置前台的url地址
        page.setUrl("client/bookServlet?action=page");
        // 3 保存Page对象到request域中
        req.setAttribute("page", page);
        // 4 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
    
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用bookService.queryByPrice(pageNo,pageSize)方法
        Page<Book> page =  bookService.pageByPrice(min, max, pageNo, pageSize);
        
        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min") != null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }
        // 新增设置前台的url地址
        page.setUrl(stringBuilder.toString());
        // 设置保存Page对象到request域中
        req.setAttribute("page",page);
        // 请求转发到/pages/client/index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    
    }
}
