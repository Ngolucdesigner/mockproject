package com.anks.tech.ecommerce.DTO;

import com.anks.tech.ecommerce.entity.Enum.Status;
import com.anks.tech.ecommerce.entity.OrderDetails;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private int orderId;
    private Status status;
    private Date orderDate;
    private double totalPrice;

    private List<OrderDetailDTO> orderDetails;

}

