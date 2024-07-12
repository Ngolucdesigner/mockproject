package com.anks.tech.ecommerce.Services.orderDetails;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailsService implements IOrderDetailsService {

}
