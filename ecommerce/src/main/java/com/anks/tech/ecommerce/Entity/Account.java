package com.anks.tech.ecommerce.Entity;


import com.anks.tech.ecommerce.Entity.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "`Account`", catalog = "ecommerce")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {


    @Column(name = "AccountId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private int id;

    @Column(name = "Username", length = 50, nullable = false, unique = true)
    private String userName;

    @Column(name = "Email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "firtname", length = 50, nullable = false)
    private String firtName;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastName;

    @Formula("concat(firtname,' ',lastname)")
    private String fullName;

    @Column(name = "avatar", length = 100)
    private String avatar;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "phone", length = 100)
    private String phone;


    @Column(name = "`password`", length = 100, nullable = false)
    private String password;

    @Column(name = "`role`")
    @Enumerated(value = EnumType.STRING)
    private Role role;


    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "UUIDKey")
    private UUID UUIDKey;




    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            createDate = new Date();
        }
        if (role == null) {
            role = Role.CUSTOMERS;
        }

    }
}
