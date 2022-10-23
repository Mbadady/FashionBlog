package com.FashionblogMbadady.FashionBlog.service;

import com.FashionblogMbadady.FashionBlog.dto.request.SignUpDto;
import com.FashionblogMbadady.FashionBlog.entity.Role;
import com.FashionblogMbadady.FashionBlog.entity.User;
import com.FashionblogMbadady.FashionBlog.repository.RoleRepository;
import com.FashionblogMbadady.FashionBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Service
public class SignUpServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
       if(userRepository.existsByEmail(signUpDto.getEmail())) {
           return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
       }
       if(userRepository.existsByUsername(signUpDto.getUsername())){
           return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
       }

       User user = new User();
       user.setEmail(signUpDto.getEmail());
       user.setName(signUpDto.getName());
       user.setUsername(signUpDto.getUsername());
       user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

       Role role = roleRepository.findByName("ROLE_ADMIN").get();

       user.setRoles(Collections.singleton(role));

     userRepository.save(user);

      return new ResponseEntity<>("Sign up successful", HttpStatus.OK);
    }
}
