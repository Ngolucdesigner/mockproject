package com.anks.tech.ecommerce.Services.Order;

import com.anks.tech.ecommerce.DTO.OrderDTO;
import com.anks.tech.ecommerce.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderServices {
    Page<Order> getAllOrder (Pageable pageable);
}
