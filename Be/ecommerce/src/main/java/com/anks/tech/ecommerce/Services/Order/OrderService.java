package com.anks.tech.ecommerce.Services.Order;


import com.anks.tech.ecommerce.dto.OrderDTO;
import com.anks.tech.ecommerce.entity.Account;
import com.anks.tech.ecommerce.entity.Enum.Status;
import com.anks.tech.ecommerce.entity.Order;
import com.anks.tech.ecommerce.repository.IAccountRepository;
import com.anks.tech.ecommerce.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private IAccountRepository accountRepository;

//    @Override
//    public void deleteOrder(Integer id) {
//
//    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }



    public List<Order> getOrderHistoryForAccount(Account account) {
        return orderRepository.findByAccountOrderByOrderDateDesc(account);
    }

    public Order updateOrderStatus(int orderId, Status status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null; //TH không tìm thấy đơn
    }

    @Override
    public Order createOrder(OrderDTO orderDTO, int accountId) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            Order order = new Order();
            order.setStatus(Status.PENDING);
            order.setAccount(account); // Liên kết đơn hàng với tài khoản

            orderRepository.save(order);

            return order;
        } else {
            throw new AccountNotFoundException("Account not found with ID: " + accountId);
        }
    }
}
