package com.anks.tech.ecommerce.Services.customer;

import com.anks.tech.ecommerce.entity.Customer;
import com.anks.tech.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {
    Page<Customer> getAllCustomers(Pageable pageable);
    ResponseEntity<?> getCustomerById(int customersId);
    ResponseEntity<?> createNewCustomer(Customer customer);
    ResponseEntity<?> updateCustomer(Customer customer, int customersId);
    ResponseEntity<?> deleteCustomer(int customersId);
}
