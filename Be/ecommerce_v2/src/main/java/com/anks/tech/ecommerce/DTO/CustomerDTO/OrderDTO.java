package com.anks.tech.ecommerce.DTO.CustomerDTO;

import com.anks.tech.ecommerce.Entity.Enum.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a", timezone = "GMT+7")
    private Date orderDate;
    private double totalPrice;
    private String paymentMethod;
    private List<OrderDetailDTO> orderDetails;

}

