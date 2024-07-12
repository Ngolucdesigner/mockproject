package com.anks.tech.ecommerce.Controller.Auth;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Entity.Enum.Role;
import com.anks.tech.ecommerce.Form.AuthForm.FileAccount;
import com.anks.tech.ecommerce.Form.AuthForm.LoginRequest;
import com.anks.tech.ecommerce.Form.AuthForm.SignupRequest;
import com.anks.tech.ecommerce.Form.AuthForm.UserInfoResponse;
import com.anks.tech.ecommerce.Repository.IAccountRepository;
import com.anks.tech.ecommerce.Security.JWT.JwtUtils;
import com.anks.tech.ecommerce.Security.Services.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
@ResponseBody
@CrossOrigin(origins = {"http://127.0.0.1:3000", "http://localhost:3000"})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountRepository accountRepository;



    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        UserInfoResponse.File avatar = new UserInfoResponse.File();

        if(userDetails.getAvatar()!=null) {
             avatar = modelMapper.map(userDetails.getAvatar(), UserInfoResponse.File.class);

            String avatarUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/products/files/")
                    .path(userDetails.getAvatar().getId()).toUriString();
            avatar.setUrl(avatarUrl);
        }



        UserInfoResponse userInfoResponse =new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                jwtCookie.getValue(),
                roles, avatar

        );
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())

                .body(userInfoResponse);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        Account user = new Account();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setPassword(signUpRequest.getPassword());
        user.setLastName(signUpRequest.getLastName());
        user.setUsername(signUpRequest.getUsername());


        user.setRole(Role.CUSTOMER);
        accountRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> logoutUser(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {


            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.setInvalidateHttpSession(true);
            logoutHandler.setClearAuthentication(true);
            logoutHandler.logout(request, response, null);



        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }
}
