package com.anks.tech.ecommerce.Specification.Product;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Entity.Product;
import com.anks.tech.ecommerce.Form.AccountForm.AccountFilterForm;
import com.anks.tech.ecommerce.Form.ProductForm.ProductFilterForm;
import com.anks.tech.ecommerce.Specification.Account.AccountSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    private static final String PRODUCt_NAME = "productName";
    private static final String PRODUCT_CODE = "productCode";
    private static final String MIN_ID = "minId";
    private static final String MAX_ID = "maxId";
    public static Specification<Product> biuldWhere(ProductFilterForm form){
        if (form==null) {
            return null;
        }

        Specification<Product> whereProductName = new ProductSpecification.SpecificationImpl(PRODUCt_NAME, form.getSearch());
        Specification<Product> whereProductCode = new ProductSpecification.SpecificationImpl(PRODUCT_CODE, form.getSearch());

        Specification<Product> whereMinId = new ProductSpecification.SpecificationImpl(MIN_ID, form.getMinId());
        Specification<Product> whereMaxId = new ProductSpecification.SpecificationImpl(MAX_ID, form.getMaxId());
        return Specification.where(whereProductName.or(whereProductCode)).and(whereMaxId.and(whereMinId));
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class SpecificationImpl implements Specification<Product> {
        private String key;
        private Object value;


        @Override
        public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {

                case PRODUCt_NAME:
                    // productName LIKE '%value%'
                    return criteriaBuilder.like(root.get("productName"), "%" + value + "%");
                case PRODUCT_CODE:
                    // product_code LIKE '%value%'
                    return criteriaBuilder.like(root.get("productCode"), "%" + value + "%");

                case MIN_ID:
                    // id >= value
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
                case MAX_ID:
                    // id <= value
                    return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
            }
            return null;
        }
    }

}
