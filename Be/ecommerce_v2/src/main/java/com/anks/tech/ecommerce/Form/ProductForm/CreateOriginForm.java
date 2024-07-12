package com.anks.tech.ecommerce.Form.ProductForm;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CreateOriginForm {

    @NotBlank(message = "manufacturer not null")
    private String manufacturer;
    @NotBlank(message = "madeIn not null")
    private String madeIn;
    @NotBlank(message = "guarantee not null")
    private String guarantee;
}
