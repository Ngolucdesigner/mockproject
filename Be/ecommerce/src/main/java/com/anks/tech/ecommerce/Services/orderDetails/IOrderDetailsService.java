package com.anks.tech.ecommerce.Services.orderDetails;

import com.anks.tech.ecommerce.Entity.Order;
import com.anks.tech.ecommerce.Entity.OrderDetails;

import java.util.List;

public interface IOrderDetailsService {
    List<OrderDetails> getOrderDetailsByOrder(Order order);
}
