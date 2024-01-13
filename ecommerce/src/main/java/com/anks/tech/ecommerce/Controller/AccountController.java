package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.DTO.AccountDTO;
import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Services.Account.IAccountServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = "application/json")
@ResponseBody
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500", "http://localhost:3000"})

public class AccountController {

    @Autowired
    private IAccountServices accountServices;

    @Autowired
    private  ModelMapper modelMapper;
    @GetMapping("/hello")
    String hello() {
        return "Hello word!";
    }

    @GetMapping
    public Page<AccountDTO> getAllAccounts(Pageable pageable) {
        Page<Account> accountPage = accountServices.getAllAccounts(pageable);
        List<Account> accounts = accountPage.getContent();

        List<AccountDTO> accountDTOS = accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(accountDTOS, pageable, accountPage.getTotalElements());
    }
}
