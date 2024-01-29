package com.anks.tech.ecommerce.Services.customer;

import com.anks.tech.ecommerce.entity.Customer;
import com.anks.tech.ecommerce.repository.ICustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService implements ICustomerService{

    @Autowired
    private ICustomersRepository customersRepository;

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customersRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> getCustomerById(int customersId) {
        Optional<Customer> customerOptional = customersRepository.findById(customersId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> createNewCustomer(Customer customer) {
        customersRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateCustomer(Customer customer, int customersId) {
        Optional<Customer> existingCustomerOptional = customersRepository.findById(customersId);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setFullName(customer.getFullName());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setEmail(customer.getEmail());
            customersRepository.save(existingCustomer);
            return new ResponseEntity<>(existingCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteCustomer(int customersId) {
        Optional<Customer> customerOptional = customersRepository.findById(customersId);
        if (customerOptional.isPresent()) {
            customersRepository.deleteById(customersId);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }
}
