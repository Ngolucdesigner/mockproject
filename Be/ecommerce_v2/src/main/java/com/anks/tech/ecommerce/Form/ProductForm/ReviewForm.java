package com.anks.tech.ecommerce.Form.ProductForm;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewForm {

    @NotBlank(message = "username not null")
    private String username;
    private double rating;
    private String reviewText;
    private int productId;

}
