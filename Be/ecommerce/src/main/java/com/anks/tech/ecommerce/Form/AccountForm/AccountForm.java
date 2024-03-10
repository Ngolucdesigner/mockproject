package com.anks.tech.ecommerce.Form.AccountForm;

import com.anks.tech.ecommerce.Validate.Account.AccountEmailNotExists;
import com.anks.tech.ecommerce.Validate.Account.AccountUserNameNotExist;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class AccountForm {

    private int accountId;
    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "username not null")
    @AccountUserNameNotExist
    private String username;

    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "email not null")
    @AccountEmailNotExists
    private String email;

    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "firstName not null")
    private String firstName;

    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "lastName not null")
    private String lastName;


    @NotBlank(message = "address not null")
    private String address;

    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "phone not null")
    private String phone;

    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "password not null")
    private String password;

    private String role;
    private FileProduct fileProduct;

    public AccountForm(String username, String email, String firstName, String lastName, String address, String phone, String password, String role) {
        this.username = username;
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
