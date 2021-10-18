package aug.test;

import aug.bean.Cart;
import aug.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(2, "狗都不学", 5, new BigDecimal(12)));
        System.out.println(cart);
    }
    
    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(2, "狗都不学", 5, new BigDecimal(12)));
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
    }
    
    @Test
    public void clear() {
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(2, "狗都不学", 5, new BigDecimal(12)));
        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
    }
    
    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(1, "时间简史", 5, new BigDecimal(12)));
        cart.addItem(new CartItem(2, "狗都不学", 5, new BigDecimal(12)));
        System.out.println(cart);
        cart.updateCount(1, 5);
        System.out.println(cart);
    }
}