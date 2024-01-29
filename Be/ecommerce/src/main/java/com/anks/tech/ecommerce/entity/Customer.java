package com.anks.tech.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@Table(name = "`customers`", catalog = "ecommerce")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Column(name = "customersId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customersId;
    @Column(name = "fullname", length = 50, nullable = false)
    private String fullName;
    @Column(name = "email" ,length = 50, nullable = false)
    private String email;
    @Column(name = "phone",  length = 50, nullable = false)
    private String phone;
    @Column(name = "address",  length = 50, nullable = false)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Order> orders;

}
