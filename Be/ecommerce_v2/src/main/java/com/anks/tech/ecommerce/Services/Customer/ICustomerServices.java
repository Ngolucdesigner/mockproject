package com.anks.tech.ecommerce.Services.Customer;

import com.anks.tech.ecommerce.Form.CustomerForm.CustomerForm;
import com.anks.tech.ecommerce.Form.CustomerForm.OrderFilterForm;
import org.springframework.data.domain.Page;
import com.anks.tech.ecommerce.Entity.Customers;
import org.springframework.data.domain.Pageable;

public interface ICustomerServices {
   public Page<Customers> getAllCustomer(Pageable pageable, OrderFilterForm form);
   public Customers getCustomerById(int id);

   public void createOder(CustomerForm form);
   public void deleteOder(int id);
}
