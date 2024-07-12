package com.anks.tech.ecommerce.Validate.Account;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(
        {
                ElementType.PARAMETER,
                ElementType.FIELD,
                ElementType.TYPE_USE,
                ElementType.CONSTRUCTOR,
                ElementType.METHOD
        }
)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountIdNotExistxValidator.class)
public @interface AccountIdNotExists {
    String message() default "{account.deleteAccount}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
