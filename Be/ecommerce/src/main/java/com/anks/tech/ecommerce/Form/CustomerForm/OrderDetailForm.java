package com.anks.tech.ecommerce.Form.CustomerForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class OrderDetailForm {


    private int productQuantity;

    private int orderId;
    private int productId;

}
