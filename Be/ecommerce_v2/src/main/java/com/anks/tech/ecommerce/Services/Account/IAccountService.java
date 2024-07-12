package com.anks.tech.ecommerce.Services.Account;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm.AccountFilterForm;
import com.anks.tech.ecommerce.Form.AccountForm.AccountForm;

import com.anks.tech.ecommerce.Form.AuthForm.LoginRequest;
import com.anks.tech.ecommerce.Form.AuthForm.SignupRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAccountService {

    Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm form);
    void createNewAccount (AccountForm form);
   Account getAccountById(Integer id);

   void updateAccount(AccountForm form);
    void deleteAccountByid(int id);

    String register(SignupRequest registerDto);
    String verifyAccount(String email, String otp);

    String regenerateOtp(String email);

    String login(LoginRequest loginDto);
}
