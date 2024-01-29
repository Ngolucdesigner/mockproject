package com.anks.tech.ecommerce.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerForm {

    private String fullName;
    private String email;
    private String phone;

    private String address;
    private String city;

    private OrderForm orderForm;


}
