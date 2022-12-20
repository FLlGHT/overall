package com.flight.overall.controller;


import com.flight.overall.dto.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/auth")
    public AccountDTO auth(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();
        logger.info(username + " logged in successfully");

        return new AccountDTO();
    }
}
