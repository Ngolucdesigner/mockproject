package com.anks.tech.ecommerce.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private int accountId;
    private double totalPrice;
    private String status;
}
