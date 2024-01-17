package com.anks.tech.ecommerce.Respository;

import com.anks.tech.ecommerce.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewReposiory extends JpaRepository<Review,Integer> {
}
