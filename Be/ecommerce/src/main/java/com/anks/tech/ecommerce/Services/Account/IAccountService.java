package com.anks.tech.ecommerce.Services.Account;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IAccountService {

    Page<Account> getAllAccounts(Pageable pageable);
    void createNewAccount (AccountForm form);
    Optional<Account> getAccountById(Integer id);
}
