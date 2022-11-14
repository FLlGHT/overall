package com.flight.overall.controller;

import com.flight.overall.dto.AccountDTO;
import com.flight.overall.exception.ProfileAlreadyExistException;
import com.flight.overall.service.AccountService;
import com.flight.overall.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RatingService ratingService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("account", new AccountDTO());

        return "authentication/registration";
    }

    @PostMapping("/registration")
    public String registerNewAccount(@ModelAttribute("account") @Valid AccountDTO account, Model model) {
        try {
            accountService.registerAndAuthenticate(account);
        } catch (ProfileAlreadyExistException alreadyExistsException) {
            model.addAttribute("account", account);
            return "redirect:/registration?error";
        }

        return "redirect:/" + account.getUsername();
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "authentication/login";
    }
}
