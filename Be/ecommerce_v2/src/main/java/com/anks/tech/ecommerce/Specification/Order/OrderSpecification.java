package com.anks.tech.ecommerce.Specification.Order;

import com.anks.tech.ecommerce.Entity.Customers;

import com.anks.tech.ecommerce.Form.CustomerForm.OrderFilterForm;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    private static final String CUSTOMER_NAME = "fullName";
    private static final String CUSTOMER_CODE = "customerCode";
    private static final String CUSTOMER_PHONE = "phone";
    private static final String CUSTOMER_EMAIL = "email";
    private static final String MIN_ID = "minId";
    private static final String MAX_ID = "maxId";


    public static Specification<Customers> biuldWhere(OrderFilterForm form){
        if (form==null) {
            return null;
        }

        Specification<Customers> whereCustomerName = new OrderSpecification.SpecificationImpl(CUSTOMER_NAME, form.getSearch());
        Specification<Customers> whereCustomerCode = new OrderSpecification.SpecificationImpl(CUSTOMER_CODE, form.getSearch());
        Specification<Customers> whereCustomerPhone = new OrderSpecification.SpecificationImpl(CUSTOMER_PHONE, form.getSearch());
        Specification<Customers> whereCustomerEmail = new OrderSpecification.SpecificationImpl(CUSTOMER_EMAIL, form.getSearch());


        Specification<Customers> whereMinId = new OrderSpecification.SpecificationImpl(MIN_ID, form.getMinId());
        Specification<Customers> whereMaxId = new OrderSpecification.SpecificationImpl(MAX_ID, form.getMaxId());
        return Specification.where(whereCustomerName.or(whereCustomerCode).or(whereCustomerEmail).or(whereCustomerPhone)).and(whereMaxId.and(whereMinId));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SpecificationImpl implements Specification<Customers> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Customers> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {

                case CUSTOMER_NAME:
                    // productName LIKE '%value%'
                    return criteriaBuilder.like(root.get("fullName"), "%" + value + "%");
                case CUSTOMER_CODE:
                    // product_code LIKE '%value%'
                    return criteriaBuilder.like(root.get("customerCode"), "%" + value + "%");

                case CUSTOMER_EMAIL:
                    // product_code LIKE '%value%'
                    return criteriaBuilder.like(root.get("email"), "%" + value + "%");

                case CUSTOMER_PHONE:
                    // product_code LIKE '%value%'
                    return criteriaBuilder.like(root.get("phone"), "%" + value + "%");

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
