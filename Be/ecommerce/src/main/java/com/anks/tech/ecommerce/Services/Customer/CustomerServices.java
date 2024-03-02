package com.anks.tech.ecommerce.Services.Customer;

import com.anks.tech.ecommerce.Entity.Customers;

import com.anks.tech.ecommerce.Entity.Order;
import com.anks.tech.ecommerce.Entity.OrderDetails;
import com.anks.tech.ecommerce.Form.CustomerForm.CustomerForm;
import com.anks.tech.ecommerce.Form.CustomerForm.OrderDetailForm;
import com.anks.tech.ecommerce.Form.CustomerForm.OrderFilterForm;
import com.anks.tech.ecommerce.Repository.ICustomersRepository;
import com.anks.tech.ecommerce.Repository.IOrderDetailRepository;
import com.anks.tech.ecommerce.Repository.IOrderRepository;
import com.anks.tech.ecommerce.Specification.Order.OrderSpecification;
import com.anks.tech.ecommerce.Specification.Product.ProductSpecification;
import com.anks.tech.ecommerce.Utils.LocalDateTimeToDateConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServices implements ICustomerServices{
    @Autowired
    private ICustomersRepository customersRepository;

    @Autowired
    private IOrderDetailRepository orderDetailRepository;
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocalDateTimeToDateConverter localDateTimeToDateConverter;

    @Override
    public Page<Customers> getAllCustomer(Pageable pageable, OrderFilterForm form){
        Specification<Customers> where = OrderSpecification.biuldWhere(form);
        return customersRepository.findAll(where,pageable);
    }

    @Override
    public Customers getCustomerById(int id) {
        return customersRepository.findById(id).get();
    }

    @Override
    public void createOder(CustomerForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(CustomerForm.class, Customers.class);
        if(typeMap==null){
            modelMapper.addMappings(new PropertyMap<CustomerForm, Customers>() {
                @Override
                protected void configure() {
                    skip(destination.getCustomersId());
                }
            });
        }

        Customers customers = modelMapper.map(form, Customers.class);
        customersRepository.save(customers);


        Order order = modelMapper.map(form.getOrderForm(),Order.class);
        order.setCustomers(customers);
        order.setOrderDate(localDateTimeToDateConverter.convertToLocalDateTimeToDate(LocalDateTime.now()));
        orderRepository.save(order);

//
        List<OrderDetailForm> orderDetailForms = form.getOrderForm().getOrderDetailForms();


        List<OrderDetails> orderDetails = orderDetailForms.stream().map(orderDetail -> modelMapper.map(orderDetail,OrderDetails.class)).collect(Collectors.toList());
//        orderDetailRepository.saveAll(orderDetails);
        orderDetails.forEach(orderDetails1 -> orderDetails1.setOrder(order));
        orderDetailRepository.saveAll(orderDetails);

    }

    @Override
    public void deleteOder(int id) {
        customersRepository.deleteById(id);
    }

}
