package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomersRepository extends JpaRepository<Customers,Integer> {
}
