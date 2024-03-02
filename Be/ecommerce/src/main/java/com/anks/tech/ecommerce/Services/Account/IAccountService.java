package com.anks.tech.ecommerce.Services.Account;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm.AccountForm;

import com.anks.tech.ecommerce.Form.AuthForm.LoginRequest;
import com.anks.tech.ecommerce.Form.AuthForm.SignupRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAccountService {

    Page<Account> getAllAccounts(Pageable pageable);
    void createNewAccount (AccountForm form);
    Optional<Account> getAccountById(Integer id);

    void deleteAccountByid(int id);

    String register(SignupRequest registerDto);
    String verifyAccount(String email, String otp);

    String regenerateOtp(String email);

    String login(LoginRequest loginDto);
}
