package aug.service.impl;

import aug.bean.Book;
import aug.bean.Page;
import aug.dao.BookDao;
import aug.dao.impl.BookDaoImpl;
import aug.service.BookService;

import java.util.List;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/20 15:00
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBookByID(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBook(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
    
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据开始的索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 秋当前页数据
        List<Book> pages = bookDao.queryPageItem(begin,pageSize);
        // 设置当前页数据
        page.setItems(pages);
        
        return page;
    }
    
    @Override
    public Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount=bookDao.queryPageTotalCount(min,max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码数
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        // 设置总页码数
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据开始的索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> pages = bookDao.queryPageItem(min, max, begin, pageSize);
        // 设置当前页数据
        page.setItems(pages);
        
        return page;
    }
}
