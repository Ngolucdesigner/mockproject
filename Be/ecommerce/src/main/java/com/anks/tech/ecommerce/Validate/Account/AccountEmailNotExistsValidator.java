package com.anks.tech.ecommerce.Validate.Account;

import com.anks.tech.ecommerce.Repository.IAccountRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;



public class AccountEmailNotExistsValidator implements ConstraintValidator<AccountEmailNotExists, String> {
    @Autowired
    private IAccountRepository accountRespository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !accountRespository.existsByEmail(email);
    }
}
