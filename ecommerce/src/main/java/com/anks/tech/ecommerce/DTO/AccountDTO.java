package com.anks.tech.ecommerce.DTO;

import com.anks.tech.ecommerce.Entity.Enum.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private int id;
    private String userName;
    private String email;
    private String firtName;
    private String lastName;
    private String fullName;
    private String avatar;
    private String phone;
    private String address;
    private Role role;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
    private UUID UUIDKey;


}
