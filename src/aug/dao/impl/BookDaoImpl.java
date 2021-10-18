package aug.dao.impl;

import aug.bean.Book;
import aug.dao.BookDao;

import java.util.List;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/20 13:38
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {

        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) value(?,?,?,?,?,?)";

        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookByID(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id = ?";
        return query(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql =  "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book";
        return queryList(Book.class,sql);
    }
    
    @Override
    public Integer queryPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryValue(sql);
        return count.intValue();
    }
    
    @Override
    public List<Book> queryPageItem(int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";
        return queryList(Book.class, sql, begin,pageSize);
    }
    
    @Override
    public Integer queryPageTotalCount(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryValue(sql, min, max);
        return count.intValue();
    }
    
    @Override
    public List<Book> queryPageItem(int min, int max, int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath " +
                "from t_book where price between ? and ? order by price limit ?,?";
        return queryList(Book.class, sql, min, max, begin, pageSize);
    }
}
