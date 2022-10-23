package com.FashionblogMbadady.FashionBlog.controller;


import com.FashionblogMbadady.FashionBlog.dto.request.LoginDto;
import com.FashionblogMbadady.FashionBlog.dto.request.SignUpDto;
import com.FashionblogMbadady.FashionBlog.dto.response.JwtAuthResponseDto;
import com.FashionblogMbadady.FashionBlog.security.JwtTokenProvider;
import com.FashionblogMbadady.FashionBlog.service.SignUpServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Rest API to Sign in and register a user")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
   private AuthenticationManager authenticationManager;

    @Autowired
    private SignUpServiceImpl signUpService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @ApiOperation(value = "Rest Api to Sign in a User")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        JwtAuthResponseDto jwtAuthResponse = new JwtAuthResponseDto(token, "Bearer");

//    return ResponseEntity.ok(new JwtAuthResponse(token));

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


    @ApiOperation(value = "Rest Api to register a User")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        return signUpService.registerUser(signUpDto);
    }
}
