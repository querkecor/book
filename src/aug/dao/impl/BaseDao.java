package aug.dao.impl;

import aug.bean.User;
import aug.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/1 21:03
 */
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();
    
    
    /**
     * @param sql
     * @param args
     * @return 返回-1说明执行失败，返回其他则表示影响的行数
     */
    public int update(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查询返回一个javaBean的sql语句
     *
     * @param tClass 返回的对象类型
     * @param sql    执行的sql语句
     * @param args   sql对应的占位符
     * @param <T>    返回的类型泛型
     * @return
     */
    public <T> T query(Class<T> tClass, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(tClass), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * 查询返回多个javaBean的sql语句
     *
     * @param tClass 返回的对象类型
     * @param sql    执行的sql语句
     * @param args   sql对应的占位符
     * @param <T>    返回的类型泛型
     * @return
     */
    public <T> List<T> queryList(Class<T> tClass, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(tClass), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * 执行特殊（一行或一列）SQL语句
     *
     * @param sql
     * @param args
     * @return
     */
    public Object queryValue(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
