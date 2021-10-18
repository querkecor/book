package aug.test;

import aug.bean.Order;
import aug.dao.OrderDao;
import aug.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {
    
    @Test
    public void saveOrder() {
    
        OrderDao orderDao = new OrderDaoImpl();
        
        orderDao.saveOrder(new Order("123456789",new Date(),new BigDecimal(100),0,1));
        
    }
}