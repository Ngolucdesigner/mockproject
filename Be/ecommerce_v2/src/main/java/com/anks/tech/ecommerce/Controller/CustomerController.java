package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.DTO.CustomerDTO.CustomerDTO;
import com.anks.tech.ecommerce.Form.CustomerForm.OrderFilterForm;
import com.anks.tech.ecommerce.Services.Customer.ICustomerServices;
import com.anks.tech.ecommerce.Form.CustomerForm.CustomerForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anks.tech.ecommerce.Entity.Customers;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
@ResponseBody

public class CustomerController {

    @Autowired

    private ICustomerServices customerServices;

    @Autowired
    private  ModelMapper modelMapper;
    @GetMapping("/hello")
    ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("hello");
    }

    @GetMapping("/all-customer")
    public ResponseEntity<Page<CustomerDTO>> getAllCustomer(Pageable pageable, OrderFilterForm form){
        Page<Customers> customersPage = customerServices.getAllCustomer(pageable, form);
        List<Customers> customers = customersPage.getContent();
        List<CustomerDTO> customerDTOS = customers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());


        return ResponseEntity.ok().body(new PageImpl<>(customerDTOS,pageable,customersPage.getTotalElements()));
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id){
        Customers customers = customerServices.getCustomerById(id);
        CustomerDTO customerDTO = modelMapper.map(customers, CustomerDTO.class);
        return ResponseEntity.ok().body(customerDTO);
    }
    @PostMapping("/new-order")
    public ResponseEntity<String> createOrder(@RequestBody CustomerForm form){

        customerServices.createOder(form);

        return ResponseEntity.ok().body("create order successfully!");
    }



    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteOrder(@PathVariable int id){
        customerServices.deleteOder(id);
        return ResponseEntity.ok().body("Delete successfully!");
    }
}
