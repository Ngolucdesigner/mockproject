package com.anks.tech.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`infomation`", catalog = "ecommerce")
@Getter
@Setter
@NoArgsConstructor

public class Information {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
