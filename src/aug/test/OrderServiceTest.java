package aug.test;

import aug.bean.Cart;
import aug.bean.CartItem;
import aug.service.OrderService;
import aug.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {
    
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        OrderService orderService = new OrderServiceImpl();
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(2, "狗都不学", 5, new BigDecimal(12)));
        System.out.println(orderService.createOrder(cart, 1));
    }
}