package com.luisetin.userBase.controllers;

import com.luisetin.userBase.models.Role;
import com.luisetin.userBase.models.User;
import com.luisetin.userBase.repositories.UserRepository;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        return "SessionID: " + request.getSession().getId();
    }


    @GetMapping("/api/user")
    public List<User> getUser(){
        return userRepository.findAll();

    }
}
