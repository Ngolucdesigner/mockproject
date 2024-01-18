package com.anks.tech.ecommerce.Services.Order;


import com.anks.tech.ecommerce.Entity.Order;
import com.anks.tech.ecommerce.Respository.IOrderRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServices implements IOrderServices{
    @Autowired
    IOrderRespository orderRespository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<Order> getAllOrder(Pageable pageable) {
        return orderRespository.findAll(pageable);
    }
}
