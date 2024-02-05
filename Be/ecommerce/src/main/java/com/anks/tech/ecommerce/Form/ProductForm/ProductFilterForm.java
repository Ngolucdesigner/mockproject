package com.anks.tech.ecommerce.Form.ProductForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductFilterForm {
    private String search;
    private Integer minId;
    private Integer maxId;
}
