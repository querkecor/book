package aug.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.Properties;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/1 19:50
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> localConn = new ThreadLocal<Connection>();
    
    static {
        try {
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @param
     * @description: 获取数据库连接
     * @return: java.sql.Connection 如果返回null，说明获取连接失败
     * @author: 86134
     * @time: 2021/9/1 19:53
     */
    public static Connection getConnection() {
        Connection conn = localConn.get();
        
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                localConn.set(conn); // 保存连接到ThreadLocal中
                conn.setAutoCommit(false); // 设置为手动提交
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    
    public static void CAC() {
        Connection conn = localConn.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        localConn.remove();
    }
    
    
    public static void RAC(){
        Connection conn = localConn.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        localConn.remove();
    }
    
    
    // /**
    //  * @param conn
    //  * @description: 关闭连接，释放数据库连接
    //  * @return: void
    //  * @author: 86134
    //  * @time: 2021/9/1 19:53
    //  */
    // public static void close(Connection conn) {
    //     if (conn != null) {
    //         try {
    //             conn.close();
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
}
