package com.anks.tech.ecommerce.Services.Order;


import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Entity.Enum.Status;
import com.anks.tech.ecommerce.Entity.Order;
import com.anks.tech.ecommerce.Respository.IOrderRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService implements IOrderService {

    @Autowired
    IOrderRespository orderRespository;
    @Autowired
    ModelMapper modelMapper;

//    @Override
//    public void deleteOrder(Integer id) {
//
//    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRespository.findById(orderId);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRespository.save(order);
    }



    public List<Order> getOrderHistoryForAccount(Account account) {
        return orderRespository.findByAccountOrderByOrderDateDesc(account);
    }

    public Order updateOrderStatus(int orderId, Status status) {
        Optional<Order> optionalOrder = orderRespository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            return orderRespository.save(order);
        }
        return null; //TH không tìm thấy đơn
    }
}
