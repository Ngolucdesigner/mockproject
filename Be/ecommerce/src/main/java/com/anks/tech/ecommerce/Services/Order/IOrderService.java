package com.anks.tech.ecommerce.Services.Order;

import com.anks.tech.ecommerce.Entity.Enum.Status;
import com.anks.tech.ecommerce.Entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.Optional;

public interface IOrderService {
//    void deleteOrder (Integer id);
    public Optional<Order> getOrderById(int orderId);
    public Order saveOrder(Order order);
    Order updateOrderStatus(int orderId, Status status);
}
