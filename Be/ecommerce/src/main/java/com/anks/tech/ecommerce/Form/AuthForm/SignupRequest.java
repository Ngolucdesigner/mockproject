package com.anks.tech.ecommerce.Form.AuthForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
