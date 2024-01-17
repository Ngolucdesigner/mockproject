package com.anks.tech.ecommerce.Services.Account;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountServices {

    Page<Account> getAllAccounts(Pageable pageable);
    void createNewAccount (AccountForm form);
}
