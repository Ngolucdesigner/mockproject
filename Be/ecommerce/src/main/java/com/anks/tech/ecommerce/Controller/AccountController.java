package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.DTO.AccountDTO.AccountDTO;
import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm.AccountForm;
import com.anks.tech.ecommerce.Form.AuthForm.SignupRequest;
import com.anks.tech.ecommerce.Services.Account.IAccountService;
import com.anks.tech.ecommerce.Validate.Account.AccountIdNotExists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@ResponseBody
@CrossOrigin(origins = {"http://127.0.0.1:3000", "http://localhost:3000"})
@Validated
public class AccountController {

    @Autowired
    private IAccountService accountServices;



    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/accounts/hello")
    String hello() {
        return "Hello word!";
    }

    @GetMapping("/accounts")
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(Pageable pageable) {
        Page<Account> accountPage = accountServices.getAllAccounts(pageable);
        List<Account> accounts = accountPage.getContent();

        List<AccountDTO> accountDTOS = accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());


        accountDTOS.forEach(accountDTO -> {
            if(accountDTO.getFile()!=null) {
                String fileDowloadUrl = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/v1/products/files/")
                        .path(accountDTO.getFile().getId()).toUriString();

                AccountDTO.File avatar = new AccountDTO.File();
                avatar.setUrl(fileDowloadUrl);
                avatar.setFileName(accountDTO.getFile().getFileName());
                avatar.setId(accountDTO.getFile().getId());
                avatar.setFileType(accountDTO.getFile().getFileType());
                accountDTO.setFile(avatar);
            }
        });

        return ResponseEntity.ok().body(new PageImpl<>(accountDTOS, pageable, accountPage.getTotalElements()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody  SignupRequest signupRequest){
        return ResponseEntity.ok().body(accountServices.register(signupRequest));
    }
    @GetMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String email,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(accountServices.verifyAccount(email, otp), HttpStatus.OK);
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(accountServices.regenerateOtp(email), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createNewAccount(
            @RequestParam("avatar") MultipartFile multipartFile,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String role
    ) throws IOException {

        String encodedPassword = password;

        AccountForm form = new AccountForm(username,email,firstName,lastName,address,phone,encodedPassword,role);

        AccountForm.FileProduct fileAvatar= new AccountForm.FileProduct();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        fileAvatar.setFileName(fileName);
        fileAvatar.setFileType(multipartFile.getContentType());
        fileAvatar.setData(multipartFile.getBytes());
        form.setFileProduct(fileAvatar);

        accountServices.createNewAccount(form);

        if (form == null) {
            return new ResponseEntity<>("Cannot create null user!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(form, HttpStatus.CREATED);
    }

    @GetMapping("accounts/{id}")
    public Optional<Account> getAccountById (@PathVariable @AccountIdNotExists Integer id) {
        return accountServices.getAccountById(id);
    }

    @DeleteMapping("accounts/delete/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable @AccountIdNotExists int id){
        accountServices.deleteAccountByid(id);
        return ResponseEntity.ok().body("Delete successfully!");
    }
}
