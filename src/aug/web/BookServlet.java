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
import java.util.List;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/20 15:37
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 新增图书后的页码数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        // 1 获取请求的参数，封装成为Book对象
        Book book = WebUtils.paramToBean(req.getParameterMap(), new Book());
        // 2 调用BookService.addBook()方法保存数据
        bookService.addBook(book);
        // 3 重定向跳转到图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    
    
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数，封装成Book对象
        Book book = WebUtils.paramToBean(req.getParameterMap(), new Book());
        // 2 调用bookServlet.updateBook(book)方法修改图书内容
        bookService.updateBook(book);
        // 3 重定向跳转到图书列表管理页面
        // /工程名/pages/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        
    }
    
    
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2 调用bookServlet.queryBook查询图书信息
        Book book = bookService.queryBook(id);
        // 3 保存图书信息到request域中
        req.setAttribute("book", book);
        // 4 请求转发到 pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }
    
    
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数id(图书编号)
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2 根据id调用bookService.deleteBook()删除图书
        bookService.deleteBook(id);
        // 3 重定向回图书列表管理页面
        // /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        
    }
    
    
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 通过BookService查询图书数据
        List<Book> books = bookService.queryBooks();
        // 2 把图书数据保存到request域中
        req.setAttribute("books", books);
        // 3 请求转发到图书管理页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
    
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
        // 新增设置前台的url地址
        page.setUrl("manager/bookServlet?action=page");
        // 3 保存Page对象到request域中
        req.setAttribute("page", page);
        // 4 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
