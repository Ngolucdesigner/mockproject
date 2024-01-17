package com.anks.tech.ecommerce.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "`OrderDetails`", catalog = "ecommerce")
@Setter
@Getter
@NoArgsConstructor
public class OrderDetails {

    @Id
    @Column(name = "OrderDetails_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderDetailsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
