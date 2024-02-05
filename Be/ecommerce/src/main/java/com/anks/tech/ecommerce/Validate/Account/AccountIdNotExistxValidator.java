package com.anks.tech.ecommerce.Validate.Account;


import com.anks.tech.ecommerce.Repository.IAccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;



public class AccountIdNotExistxValidator implements ConstraintValidator<AccountIdNotExists, Integer> {
    @Autowired
    private IAccountRepository accountRespository;
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return accountRespository.existsById(id);
    }
}
