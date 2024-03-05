package com.anks.tech.ecommerce.Validate.Account;

import com.anks.tech.ecommerce.Repository.IAccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountUsernameExistsValidator implements ConstraintValidator<AccountUsernameExists, String> {
    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return accountRepository.existsByUsername(username);
    }
}
