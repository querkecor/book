package aug.service;

import aug.bean.Book;
import aug.bean.Page;

import java.util.List;

public interface BookService {

    public void addBook(Book book);

    public void deleteBook(Integer id);

    public void updateBook(Book book);

    public Book queryBook(Integer id);

    public List<Book> queryBooks();
    
    public Page<Book> page(int pageNo, int pageSize);
    
    public Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize);
}
