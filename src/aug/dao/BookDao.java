package aug.dao;

import aug.bean.Book;
import aug.bean.Page;

import java.util.List;

public interface BookDao {

    public int addBook(Book book);

    public int deleteBookByID(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();
    
    public Integer queryPageTotalCount();
    
    public List<Book> queryPageItem(int begin, int pageSize);
    
    public Integer queryPageTotalCount(int min, int max);
    
    public List<Book> queryPageItem(int min, int max, int begin, int pageSize);
}
