package com.anks.tech.ecommerce.Form.AuthForm;

import com.anks.tech.ecommerce.Validate.Account.AccountEmailExists;
import com.anks.tech.ecommerce.Validate.Account.AccountUsernameExists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @AccountUsernameExists
    private String username;
    private String password;
}

