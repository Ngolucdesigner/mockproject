package com.anks.tech.ecommerce.Respository;

import com.anks.tech.ecommerce.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailsRespository extends JpaRepository<OrderDetails, Integer> {
}
