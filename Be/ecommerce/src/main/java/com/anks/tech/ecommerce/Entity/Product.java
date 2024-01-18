package com.anks.tech.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@Table(name = "`products`", catalog = "ecommerce")
@Setter
@Getter
@NoArgsConstructor

public class Product {

    @Column(name = "productid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "productName", length = 100, nullable = false)
    private String productName;

//    @Lob
    @Column(name = "imgUrl",columnDefinition = "TEXT", length = 100, nullable = false)
    private String imgUrl;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "priceSale")
    private double priceSales;

    @Column(name = "shortDesc",columnDefinition = "TEXT", nullable = false)
    private String shortDesc;
    @Column(name = "`description`", columnDefinition = "TEXT",nullable = false)
    private String description;
    @Column(name = "avgRating", nullable = false)
    private double avgRating;

    @OneToOne
    @JoinColumn(name = "catagoryId", referencedColumnName = "categoryid")
    private Category category;

    @OneToOne
    @JoinColumn(name = "uuidUrl", referencedColumnName = "uuid")
    private FileProduct fileProduct;

    @OneToOne
    @JoinColumn(name = "originId" ,referencedColumnName = "id")
    private Origin origin;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<OrderDetails> orderDetails;

    @PrePersist
    public void prePersist() {
        if(this.priceSales == 0){
            this.priceSales=1;
        }


    }
}
