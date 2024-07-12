package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
