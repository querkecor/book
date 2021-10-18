package aug.dao.impl;

import aug.dao.OrderDao;

/**
 * @description:
 * @author: 86134
 * @time: 2021/10/9 19:31
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(aug.bean.Order order) {
        System.out.println("saveOrder程序在【"+ Thread.currentThread().getName() +"】");
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
