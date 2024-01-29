package com.anks.tech.ecommerce.Services.Customer;

import com.anks.tech.ecommerce.form.CustomerForm;
import org.springframework.data.domain.Page;
import com.anks.tech.ecommerce.Entity.Customers;
import org.springframework.data.domain.Pageable;

public interface ICustomerServices {
   public Page<Customers> getAllCustomer(Pageable pageable);
   public Customers getCustomerById(int id);

   public void createOder(CustomerForm form);

}
