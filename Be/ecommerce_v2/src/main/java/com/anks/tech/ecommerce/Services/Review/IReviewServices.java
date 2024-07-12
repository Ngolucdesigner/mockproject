package com.anks.tech.ecommerce.Services.Review;

import com.anks.tech.ecommerce.Entity.Review;
import com.anks.tech.ecommerce.Form.ProductForm.ReviewForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReviewServices {

    void createReview(ReviewForm form);

    Page<Review> getAllReview(Pageable pageable);
}
