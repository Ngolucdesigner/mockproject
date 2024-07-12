package com.anks.tech.ecommerce.Form.AccountForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountFilterForm {
    private String search;
    private Integer minId;
    private Integer maxId;
}
