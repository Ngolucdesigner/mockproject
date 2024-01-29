package com.anks.tech.ecommerce.controller;

import com.anks.tech.ecommerce.DTO.AuthRequest;
import com.anks.tech.ecommerce.DTO.AuthResponse;
import com.anks.tech.ecommerce.config.security.JwtUtil;
import com.anks.tech.ecommerce.Services.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@ResponseBody
@CrossOrigin(origins = {"http://127.0.0.1:3000", "http://localhost:3000"})

public class AuthController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password");
//        }
//
//        final UserDetails userDetails = customUserDetailsService
//                .loadUserByUsername(authRequest.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        AuthResponse authResponse = new AuthResponse(jwt, userDetails);
//
//        return ResponseEntity.ok(authResponse);
//    }
}
