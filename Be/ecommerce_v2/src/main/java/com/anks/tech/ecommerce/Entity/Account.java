package com.anks.tech.ecommerce.Entity;


import com.anks.tech.ecommerce.Entity.Enum.Role;
import com.anks.tech.ecommerce.Utils.LocalDateTimeToDateConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;


@Entity
@Data
@Table(name = "`account`", catalog = "ecommerce")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {



    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @GeneratedValue(generator = "uuid2")
//  @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private int accountId;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "firstname", length = 50)
    private String firstName;

    @Column(name = "lastname", length = 50)
    private String lastName;

    @Formula("concat(firstname,' ',lastname)")
    private String fullName;


    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone", length = 100)
    private String phone;


    @Column(name = "`password`", columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(name = "`role`")
    @Enumerated(value = EnumType.STRING)
    private Role role;


    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UUIDKey")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String UUIDKey;

    @Column(name = "`active`")
    private Boolean active;
    @Column(name = "`otp`", unique = true, nullable = false)
    private String otp;

    @OneToOne
    @JoinColumn(name = "avatar", referencedColumnName = "uuid")
    private FileProduct fileProduct;


    @PrePersist
    public void prePersist() {
        if (this.createDate == null) {
            this.createDate = new Date();
        }
        if (this.role == null) {
            this.role = Role.CUSTOMER;
        }
        if(this.getUUIDKey()==null){
            this.UUIDKey = UUID.randomUUID().toString();
        }

    }
}
