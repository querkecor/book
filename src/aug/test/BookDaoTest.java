package aug.test;

import aug.bean.Book;
import aug.dao.BookDao;
import aug.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"徐凤年","总管",new BigDecimal(2500),1,0,null));
    }

    @Test
    public void deleteBookByID() {
        bookDao.deleteBookByID(22);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"徐凤年","烽火戏诸侯",new BigDecimal(2500),1,0,null));
    }

    @Test
    public void queryById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
    
    @Test
    public void queryPageTotalCount() {
        System.out.println(bookDao.queryPageTotalCount());
    }
    
    @Test
    public void queryPageItem() {
        for (Book book : bookDao.queryPageItem(12, 4)) {
            System.out.println(book);
        }
    }
    
    @Test
    public void queryPageTotalCountByPrice() {
        System.out.println(bookDao.queryPageTotalCount(10, 50));
    }
    
    @Test
    public void queryPageItemByPrice() {
        for (Book book : bookDao.queryPageItem(10, 50, 1, 4)) {
            System.out.println(book);
        }
    }
    
}