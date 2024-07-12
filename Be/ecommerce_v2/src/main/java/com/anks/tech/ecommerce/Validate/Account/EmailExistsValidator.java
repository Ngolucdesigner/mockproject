package com.anks.tech.ecommerce.Validate.Account;

import com.anks.tech.ecommerce.Repository.IAccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailExistsValidator implements ConstraintValidator<AccountEmailExists, String> {

    @Autowired
    IAccountRepository accountRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return accountRepository.existsByEmail(email);
    }
}
