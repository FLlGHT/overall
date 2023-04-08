package com.flight.overall.controller;


import com.flight.overall.dto.AccountDTO;
import com.flight.overall.dto.MessageResponse;
import com.flight.overall.exception.ProfileAlreadyExistException;
import com.flight.overall.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  private Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private AccountService accountService;

  @GetMapping("/auth")
  public AccountDTO auth(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    String username = userDetails.getUsername();
    logger.info(username + " logged in successfully");

    return new AccountDTO();
  }

  @PostMapping("/registration")
  public ResponseEntity<MessageResponse> registerNewAccount(@RequestBody AccountDTO account) {
    logger.info("Trying to register an account {}", account);
    try {
      accountService.registerAndAuthenticate(account);
    }
    catch (ProfileAlreadyExistException alreadyExistsException) {
      logger.info("User with name {} already exists", account.getUsername());
      return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new MessageResponse("Username " + account.getUsername() + " already exists"));
    }

    logger.info("{} register success", account.getUsername());
    return ResponseEntity.ok()
      .body(new MessageResponse("register success"));
  }
}
