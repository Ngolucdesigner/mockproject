package com.anks.tech.ecommerce.Form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewForm {
    String username;
    double rating;
    String reviewText;
    int productId;

}
