package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Entity.Enum.Status;
import com.anks.tech.ecommerce.Entity.Order;
import com.anks.tech.ecommerce.Entity.OrderDetails;
import com.anks.tech.ecommerce.Services.Account.AccountService;
import com.anks.tech.ecommerce.Services.Order.OrderService;
import com.anks.tech.ecommerce.Services.orderDetails.OrderDetailsService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;
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
    public Optional<Order> getOrderById (@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping ("/account/{accountId}")
    public List<Order> getOrderHistoryForAccount (@PathVariable int accountId) {
        Account account = new Account();
        return orderService.getOrderHistoryForAccount(account);
    }

    @GetMapping ("/{orderId}/order-details")
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
    public ResponseEntity<Order> createOrder (@RequestBody Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = new Account();
        account.setAccountId(userDetails.getAccountId());
        return orderService.saveOrder(order);
    }

    @PutMapping ("/{orderId}/status")
    public Order updateOrderStatus (@PathVariable int orderId, @RequestParam Status status) {
       return orderService.updateOrderStatus(orderId, status);
    }
}
