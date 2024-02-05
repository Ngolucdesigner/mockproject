package com.anks.tech.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "`reviews`", catalog = "ecommerce")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Column(name = "reviewid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "reviewText", columnDefinition = "TEXT")
    private String reviewText;

    @Column(name = "rating")
    private double rating;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
