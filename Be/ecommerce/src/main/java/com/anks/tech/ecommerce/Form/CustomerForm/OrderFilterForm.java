package com.anks.tech.ecommerce.Form.CustomerForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderFilterForm {

    private String search;
    private Integer minId;
    private Integer maxId;
}
