package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.DTO.AccountDTO;
import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm;
import com.anks.tech.ecommerce.Services.Account.IAccountServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
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
    private ModelMapper modelMapper;

    @GetMapping("/hello")
    String hello() {
        return "Hello word!";
    }

    @GetMapping
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(Pageable pageable) {
        Page<Account> accountPage = accountServices.getAllAccounts(pageable);
        List<Account> accounts = accountPage.getContent();

        List<AccountDTO> accountDTOS = accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());

        accountDTOS.forEach(accountDTO -> {
            String fileDowloadUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/products/files/")
                    .path(accountDTO.getFile().getId()).toUriString();
            AccountDTO.File avatar = new AccountDTO.File();
            avatar.setUrl(fileDowloadUrl);
            avatar.setFileName(accountDTO.getFile().getFileName());
            avatar.setId(accountDTO.getFile().getId());
            avatar.setFileType(accountDTO.getFile().getFileType());
            accountDTO.setFile(avatar);
        });


        return ResponseEntity.ok().body(new PageImpl<>(accountDTOS, pageable, accountPage.getTotalElements()));
    }

    @PostMapping("/new-account")
    public ResponseEntity<String> createNewAccount(
            @RequestParam("avatar") MultipartFile multipartFile,
            @RequestParam String userName,
            @RequestParam String email,
            @RequestParam String firtName,
            @RequestParam String lastName,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam String role
    ) throws IOException {

        AccountForm form = new AccountForm(userName,email,firtName,lastName,address,phone,password,role);

        AccountForm.FileProduct fileAvatar= new AccountForm.FileProduct();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        fileAvatar.setFileName(fileName);
        fileAvatar.setFileType(multipartFile.getContentType());
        fileAvatar.setData(multipartFile.getBytes());
        form.setFileProduct(fileAvatar);

        accountServices.createNewAccount(form);

        return ResponseEntity.ok().body("Create successfully!");
    }
}