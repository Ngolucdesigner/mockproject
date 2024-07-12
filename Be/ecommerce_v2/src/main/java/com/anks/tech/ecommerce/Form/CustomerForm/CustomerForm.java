package com.anks.tech.ecommerce.Form.CustomerForm;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerForm {

    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "fullName not null")
    private String fullName;
    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "fullName not null")
    private String email;
    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "phone number not null")
    private String phone;


    @NotBlank(message = "Address not null")
    private String address;
    private String city;

    private OrderForm orderForm;


}
