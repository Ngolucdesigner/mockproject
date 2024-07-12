package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {

    Product findProductByProductCode(String productCode);
}
