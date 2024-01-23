package com.anks.tech.ecommerce.Services.Order;

import com.anks.tech.ecommerce.dto.OrderDTO;
import com.anks.tech.ecommerce.entity.Enum.Status;
import com.anks.tech.ecommerce.entity.Order;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

public interface IOrderService {
//    void deleteOrder (Integer id);
    public Optional<Order> getOrderById(int orderId);
    public Order saveOrder(Order order);
    Order updateOrderStatus(int orderId, Status status);
    Order createOrder(OrderDTO orderDTO, int accountId) throws AccountNotFoundException;
}
