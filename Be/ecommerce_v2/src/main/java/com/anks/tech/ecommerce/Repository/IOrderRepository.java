package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,Integer> {

}
