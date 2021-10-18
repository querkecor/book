package aug.service.impl;

import aug.bean.*;
import aug.dao.BookDao;
import aug.dao.OrderDao;
import aug.dao.OrderItemDao;
import aug.dao.impl.BookDaoImpl;
import aug.dao.impl.OrderDaoImpl;
import aug.dao.impl.OrderItemDaoImpl;

import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: 86134
 * @time: 2021/10/9 20:14
 */
public class OrderServiceImpl implements aug.service.OrderService {
    
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    
    @Override
    public String createOrder(Cart cart, Integer userId) {
    
        System.out.println("createOrder程序在【"+ Thread.currentThread().getName() +"】");
        
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        for (Map.Entry<Integer ,CartItem>entry : cart.getItems().entrySet()){
            
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount());
            book.setStock( book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
