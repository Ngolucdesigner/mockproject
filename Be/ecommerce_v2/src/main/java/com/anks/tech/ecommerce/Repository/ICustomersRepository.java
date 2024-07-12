package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Customers;
import com.anks.tech.ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICustomersRepository extends JpaRepository<Customers,Integer>, JpaSpecificationExecutor<Customers> {
}
