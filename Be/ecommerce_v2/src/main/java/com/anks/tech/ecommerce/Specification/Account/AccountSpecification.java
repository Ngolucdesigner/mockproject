package com.anks.tech.ecommerce.Specification.Account;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm.AccountFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {

    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String MIN_ID = "minId";
    private static final String MAX_ID = "maxId";
    private static final String FULL_NAME = "fullName";


    public static Specification<Account> biuldWhere(AccountFilterForm form){
        if (form==null) {
            return null;
        }

        Specification<Account> whereUsername = new SpecificationImpl(USERNAME, form.getSearch());
        Specification<Account> whereEmail = new SpecificationImpl(EMAIL, form.getSearch());
        Specification<Account> whereFullName = new SpecificationImpl(FULL_NAME, form.getSearch());
        Specification<Account> whereMinId = new SpecificationImpl(MIN_ID, form.getMinId());
        Specification<Account> whereMaxId = new SpecificationImpl(MAX_ID, form.getMaxId());
        return Specification.where(whereUsername.or(whereFullName).or(whereEmail)).and(whereMaxId.and(whereMinId));
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class SpecificationImpl implements Specification<Account> {
        private String key;
        private Object value;


        @Override
        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {

                case USERNAME:
                    // username LIKE '%value%'
                    return criteriaBuilder.like(root.get("username"), "%" + value + "%");
                case EMAIL:
                    // username LIKE '%value%'
                    return criteriaBuilder.like(root.get("email"), "%" + value + "%");
                case FULL_NAME:
                    return criteriaBuilder.like(root.get("fullName"), "%" + value + "%");
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
