package com.anks.tech.ecommerce.Form;

import com.anks.tech.ecommerce.Entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountForm {

    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String password;
    private String role;
    private FileProduct fileProduct;

    public AccountForm(String userName, String email, String firstName, String lastName, String address, String phone, String password, String role) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileProduct{
        private String id;
        private String fileName;
        private String fileType;
        private byte[] data;
    }


}
