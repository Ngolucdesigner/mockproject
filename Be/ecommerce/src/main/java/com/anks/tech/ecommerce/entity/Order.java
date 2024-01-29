package com.anks.tech.ecommerce.entity;

import com.anks.tech.ecommerce.entity.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "`orders`", catalog = "ecommerce")
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "`status`")
    @Enumerated(value = EnumType.STRING)
    private Status status;


    @Column(name = "`date`")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "totalPrice")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<OrderDetails> orderDetails;

    @PrePersist
    public void prePersist() {
        if (this.orderDate == null) {
            this.orderDate = new Date();
        }
    }
}
