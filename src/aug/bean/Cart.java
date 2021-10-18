package aug.bean;

import java.math.BigDecimal;
import java.util.*;

/**
 * @description:
 * @author: 86134
 * @time: 2021/10/1 11:38
 */
public class Cart {
    // private Integer totalCount;
    // private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<>();
    
    /**
     * 如已存在商品项则增加商品数量，否则增加新的商品项
     *
     * @param cartItem 商品对象
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            // 增加新的商品项
            items.put(cartItem.getId(), cartItem);
        } else {
            // 数量累加
            item.setCount(item.getCount() + cartItem.getCount());
            // 更新总金额
            item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    
    /**
     * 删除商品项
     *
     * @param id 商品项ID
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }
    
    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }
    
    /**
     * 修改商品数量
     */
    public void updateCount(Integer id, Integer count) {
        // 查看购物车中是否有商品项
        CartItem item = items.get(id);
        // 有，则修改商品数量
        if (item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }
    
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }
    
    public Map<Integer, CartItem> getItems() {
        return items;
    }
    
    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
