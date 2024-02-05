package com.anks.tech.ecommerce.Entity;

import com.anks.tech.ecommerce.Entity.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

    @Column(name = "payment")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "customersId")
    private Customers customers;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<OrderDetails> orderDetails;

    @PrePersist
    public void prePersist() {
        Date date = new Date();
        TimeZone gmtPlus7 = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(gmtPlus7);
        String dateFormat = sdf.format(date);
        if (this.orderDate == null) {
            try {
                this.orderDate =  sdf.parse(dateFormat);
                System.err.println(sdf.parse(dateFormat));
            }
            catch (Exception e){
                System.err.println(e.toString());
            }

        }
    }
}
