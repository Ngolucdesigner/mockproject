package com.anks.tech.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "`orderDetails`", catalog = "ecommerce")
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetails {
    @Id
    @Column(name = "orderDetailsId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailsId;

    @Column(name = "productQuantity")
    private int productQuantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "price_sales")
    private Double priceSales;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
