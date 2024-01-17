package com.anks.tech.ecommerce.Entity;

import com.anks.tech.ecommerce.Entity.Enum.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`oder`", catalog = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oderId;



    @Column(name = "`status`")
    @Enumerated(value = EnumType.STRING)
    private Status status;


    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails;


}
