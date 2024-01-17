package com.anks.tech.ecommerce.Entity;

import com.anks.tech.ecommerce.Entity.Enum.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`oder`", catalog = "ecommerce")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @Column(name = "`status`")
    @Enumerated(value = EnumType.STRING)
    private Status status;




}
