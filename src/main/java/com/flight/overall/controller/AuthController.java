package com.flight.overall.controller;


import com.flight.overall.dto.AccountDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Profile;
import com.flight.overall.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {

    @Autowired
    private AccountRepository accountRepository;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/auth")
    public AccountDTO auth() {
        return new AccountDTO();
    }
}
