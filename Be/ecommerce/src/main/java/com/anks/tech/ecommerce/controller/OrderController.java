package com.anks.tech.ecommerce.controller;

import com.anks.tech.ecommerce.entity.Account;
import com.anks.tech.ecommerce.entity.Enum.Status;
import com.anks.tech.ecommerce.entity.Order;
import com.anks.tech.ecommerce.entity.OrderDetails;
import com.anks.tech.ecommerce.Services.Account.AccountService;
import com.anks.tech.ecommerce.Services.Order.OrderService;
import com.anks.tech.ecommerce.Services.orderDetails.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping ("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        Optional<Order> optionalOrder = orderService.getOrderById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order);
        } else {
            // Xử lý trường hợp không tìm thấy đơn hàng
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/account/{accountId}")
    public List<Order> getOrderHistoryForAccount (@PathVariable int accountId) {
        Optional<Account> optionalAccount = accountService.getAccountById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return orderService.getOrderHistoryForAccount(account);
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping ("/{orderId}/details")
    public List<OrderDetails> getOrderDetailsForOrder (@PathVariable int orderId) {
        Optional<Order> optionalOrder = orderService.getOrderById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return orderDetailsService.getOrderDetailsByOrder(order);
        } else {
            return Collections.emptyList();
        }
    }

    @PostMapping
    public Order createOrder (@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @PutMapping ("/{orderId}/status")
    public Order updateOrderStatus (@PathVariable int orderId, @RequestParam Status status) {
       return orderService.updateOrderStatus(orderId, status);
    }
}
