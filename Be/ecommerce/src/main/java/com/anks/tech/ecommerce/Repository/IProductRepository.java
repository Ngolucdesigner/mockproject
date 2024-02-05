package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Integer> {


}
