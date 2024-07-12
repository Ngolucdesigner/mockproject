package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.DTO.AccountDTO.AccountDTO;
import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Form.AccountForm.AccountFilterForm;
import com.anks.tech.ecommerce.Form.AccountForm.AccountForm;
import com.anks.tech.ecommerce.Form.AuthForm.FileAccount;
import com.anks.tech.ecommerce.Form.AuthForm.SignupRequest;
import com.anks.tech.ecommerce.Services.Account.IAccountService;
import com.anks.tech.ecommerce.Validate.Account.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

@Validated

@Tag(name = "Account Controller")
public class AccountController {

    @Autowired
    private IAccountService accountServices;

    @Autowired
    private PasswordEncoder passwordEncoder;
                
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/accounts/hello")
    String hello() {
        return "Hello word!";
    }

    @Operation(summary = "Get all User", description = "API Get all user")
    @GetMapping("/accounts")
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(Pageable pageable, AccountFilterForm form) {
        Page<Account> accountPage = accountServices.getAllAccounts(pageable, form);
        List<Account> accounts = accountPage.getContent();

        List<AccountDTO> accountDTOS = accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());


        accountDTOS.forEach(accountDTO -> {
            if(accountDTO.getFile()!=null) {
                String fileDowloadUrl = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/products/files/")
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

    @PostMapping(value = "/register")

    public ResponseEntity<?> register(
                                      @RequestParam("avatar") MultipartFile multipartFile,
                                      @RequestParam @AccountUserNameNotExist String username,
                                      @RequestParam @AccountEmailNotExists String email,
                                      @RequestParam String password,
                                      @RequestParam(required = false) String firstName,
                                      @RequestParam(required = false) String lastName
    )throws IOException {
        SignupRequest signupRequest = new SignupRequest();

        signupRequest.setUsername(username);
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);
        signupRequest.setLastName(lastName);
        signupRequest.setFirstName(firstName);

        FileAccount fileAccount = new FileAccount();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        fileAccount.setFileName(fileName);
        fileAccount.setFileType(multipartFile.getContentType());
        fileAccount.setData(multipartFile.getBytes());
        signupRequest.setFileAccount(fileAccount);



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

    @PostMapping("/create-account")
    public ResponseEntity<?> createNewAccount(
            @RequestParam("avatar") MultipartFile multipartFile,
            @RequestParam @AccountEmailNotExists String username,
            @RequestParam @AccountEmailNotExists String email,
            @RequestParam String password,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String role
    ) throws IOException {



        AccountForm form = new AccountForm(username,email,firstName,lastName,address,phone,passwordEncoder.encode(password),role);

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

        return  ResponseEntity.ok().body("Create successfully!");
    }

    @PutMapping("accounts/update-account/{id}")
    ResponseEntity<?> updateAccount( @PathVariable @AccountIdNotExists int id,
                                    @RequestParam("avatar") MultipartFile multipartFile,
                                    @RequestParam @AccountEmailNotExists String username,
                                    @RequestParam @AccountEmailNotExists String email,
                                    @RequestParam String password,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String address,
                                    @RequestParam(required = false) String phone,
                                    @RequestParam(required = false) String role
    ) throws Exception{

        AccountForm accountForm = new AccountForm(username,email,firstName,lastName,address,phone,password,role);
        accountForm.setAccountId(id);
        AccountForm.FileProduct avatarUpdate = new AccountForm.FileProduct();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        avatarUpdate.setFileName(fileName);
        avatarUpdate.setFileType(multipartFile.getContentType());
        avatarUpdate.setData(multipartFile.getBytes());
        accountForm.setFileProduct(avatarUpdate);
        accountServices.updateAccount(accountForm);
        return ResponseEntity.accepted().body("update successfully!");
    }

    @GetMapping("accounts/{id}")
    public ResponseEntity<?> getAccountById (@PathVariable @AccountIdNotExists Integer id) {

        Account account = accountServices.getAccountById(id);

        AccountDTO accountDTO = modelMapper.map(account,AccountDTO.class);
        return ResponseEntity.ok().body(accountDTO);
    }

    @DeleteMapping("accounts/delete/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable @AccountIdNotExists int id){
        accountServices.deleteAccountByid(id);
        return ResponseEntity.accepted().body("Delete successfully!");
    }
}
