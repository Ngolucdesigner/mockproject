package com.anks.tech.ecommerce.Services.orderDetails;

import com.anks.tech.ecommerce.Entity.Order;
import com.anks.tech.ecommerce.Entity.OrderDetails;
import com.anks.tech.ecommerce.Respository.IOrderDetailsRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailsService implements IOrderDetailsService{
    private IOrderDetailsRespository orderDetailsRespository;

    @Override
    public List<OrderDetails> getOrderDetailsByOrder(Order order) {
        return orderDetailsRespository.findByOrder(order);
    }
}
