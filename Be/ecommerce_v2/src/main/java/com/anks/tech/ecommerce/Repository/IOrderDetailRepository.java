package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetails, Integer> {

}
