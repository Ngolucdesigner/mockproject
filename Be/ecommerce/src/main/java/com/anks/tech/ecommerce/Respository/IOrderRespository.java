package com.anks.tech.ecommerce.Respository;

import com.anks.tech.ecommerce.DTO.OrderDTO;
import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRespository extends JpaRepository<Order,Integer> {
    List<Order> findByAccountOrderByOrderDateDesc (Account account);
}
