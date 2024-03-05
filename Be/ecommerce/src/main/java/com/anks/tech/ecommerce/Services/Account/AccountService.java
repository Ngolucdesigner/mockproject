package com.anks.tech.ecommerce.Services.Account;

import com.anks.tech.ecommerce.Entity.Account;

import com.anks.tech.ecommerce.Entity.FileProduct;
import com.anks.tech.ecommerce.Form.AccountForm.AccountFilterForm;
import com.anks.tech.ecommerce.Form.AccountForm.AccountForm;
import com.anks.tech.ecommerce.Form.AuthForm.LoginRequest;
import com.anks.tech.ecommerce.Form.AuthForm.SignupRequest;
import com.anks.tech.ecommerce.Repository.IAccountRepository;
import com.anks.tech.ecommerce.Repository.IFileProductRepository;
import com.anks.tech.ecommerce.Specification.Account.AccountSpecification;
import com.anks.tech.ecommerce.Utils.EmailUtil;
import com.anks.tech.ecommerce.Utils.LocalDateTimeToDateConverter;
import com.anks.tech.ecommerce.Utils.OtpUtil;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService implements IAccountService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private LocalDateTimeToDateConverter localDateTimeToDateConverter;

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IFileProductRepository fileProductRespository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm form) {
        Specification<Account> where = AccountSpecification.biuldWhere(form);
        return accountRepository.findAll(where, pageable);
    }

    @Override
    public void createNewAccount(AccountForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(AccountForm.class, Account.class);

        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<AccountForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getPassword()); //bỏ qua mapping password
                }
            });
        }

        Account account = modelMapper.map(form, Account.class);

        // Mã hóa mật khẩu và set nó sau khi mapping
        String encodedPassword = form.getPassword();
        account.setPassword(encodedPassword);
        accountRepository.save(account);

        FileProduct fileAvatar = account.getFileProduct();
        fileProductRespository.save(fileAvatar);
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account;
    }

    @Override
    public void deleteAccountByid(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public String register(SignupRequest registerDto)  {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(registerDto.getLastName() ,registerDto.getEmail(), otp);

        } catch (MessagingException e) {



            throw new RuntimeException("Unable to send otp please try again");
        }
        catch ( UnsupportedEncodingException e) {

            throw new RuntimeException("Unable to send otp please try again");
        }

        Account user = new Account();
        user.setUsername(registerDto.getUsername());
        user.setLastName(registerDto.getLastName());
        user.setFirstName(registerDto.getFirstName());
        user.setEmail(registerDto.getEmail());
        user.setPhone("0");
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setOtp(otp);
        user.setActive(false);
        user.setCreateDate(localDateTimeToDateConverter.
                convertToLocalDateTimeToDate(LocalDateTime.now()));

        accountRepository.save(user);
        return "User registration successful";
    }

    @Override
    public String verifyAccount(String email, String otp) {
        Account user = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(localDateTimeToDateConverter.convertToDateToLocalDateTime(user.getCreateDate()),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setActive(true);
            accountRepository.save(user);
            return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
    }

    @Override
    public String regenerateOtp(String email) {
        Account user = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(user.getFullName(), email, otp);
        } catch (MessagingException e) {

            throw new RuntimeException("Unable to send otp please try again");
        }
        catch (UnsupportedEncodingException e) {

           throw new RuntimeException("Unable to send otp please try again");

        }
        user.setOtp(otp);
        user.setCreateDate(localDateTimeToDateConverter.convertToLocalDateTimeToDate(LocalDateTime.now()));
        accountRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }

    @Override
    public String login(LoginRequest loginDto) {
        Account user = accountRepository.findByEmail(loginDto.getUsername())
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email: " + loginDto.getUsername()));
        if (!loginDto.getPassword().equals(user.getPassword())) {
            return "Password is incorrect";
        } else if (!user.getActive()) {
            return "your account is not verified";
        }
        return "Login successful";
    }


}
