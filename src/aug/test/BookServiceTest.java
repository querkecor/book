package aug.test;

import aug.bean.Book;
import aug.bean.Page;
import aug.service.BookService;
import aug.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"白狐狸脸","总管",new BigDecimal(2500),1,0,null));
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(21);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(24,"白狐狸脸","烽火戏诸侯",new BigDecimal(2500),1,0,null));

    }

    @Test
    public void queryBook() {
        System.out.println(bookService.queryBook(24));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookService.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(2, Page.PAGE_SIZE));
    }
    
    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(10, 50, 1, Page.PAGE_SIZE));
    }
}