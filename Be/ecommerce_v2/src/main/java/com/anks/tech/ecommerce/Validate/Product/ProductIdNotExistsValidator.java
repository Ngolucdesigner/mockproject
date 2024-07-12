package com.anks.tech.ecommerce.Validate.Product;

import com.anks.tech.ecommerce.Repository.IProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductIdNotExistsValidator implements ConstraintValidator<ProductIdNotExists, Integer> {


    @Autowired
    private IProductRepository productRepository;
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsById(id);
    }
}
