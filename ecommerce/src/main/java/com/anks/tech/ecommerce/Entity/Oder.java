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
public class Oder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "`status`")
    @Enumerated(value = EnumType.STRING)
    Status status;



}
