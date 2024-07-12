package com.anks.tech.ecommerce.Form.AuthForm;

import com.anks.tech.ecommerce.Validate.Account.AccountEmailNotExists;
import com.anks.tech.ecommerce.Validate.Account.AccountUserNameNotExist;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

//    @NotBlank(message = "username not null")
//    @AccountUserNameNotExist
    private String username;
//    @NotBlank(message = "email not null")
//    @AccountEmailNotExists
    private String email;
//    @Length(max = 50, message = "max length 50")
//    @NotBlank(message = "password not null")
    private String password;
//    @Length(max = 50, message = "max length 50")
//    @NotBlank(message = "firstName not null")
    private String firstName;
//    @Length(max = 50, message = "max length 50")
//    @NotBlank(message = "lastName not null")
    private String lastName;

    private FileAccount fileAccount;
}
