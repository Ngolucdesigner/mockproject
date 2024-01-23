package com.anks.tech.ecommerce.Respository;

import com.anks.tech.ecommerce.Entity.Order;
import com.anks.tech.ecommerce.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderDetailsRespository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByOrder (Order order);
}
