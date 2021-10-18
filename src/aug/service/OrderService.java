package aug.service;

import aug.bean.Cart;

public interface OrderService {
    
    public String createOrder(Cart cart ,Integer userId);
}
