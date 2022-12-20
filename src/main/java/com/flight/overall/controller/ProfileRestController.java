package com.flight.overall.controller;


import com.flight.overall.dto.GradeDTO;
import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Profile;
import com.flight.overall.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileRestController {

    @Autowired
    private ProfileService profileService;

    private Logger logger = LoggerFactory.getLogger(ProfileRestController.class);

    @GetMapping("/{username}")
    public ProfileDTO getProfile(@PathVariable String username,
                                 Authentication authentication) {

        Account account = (Account) authentication.getPrincipal();
        logger.info("account " + account.getUsername() + " view " + username + " page");

        Optional<Profile> profile = profileService.getProfile(username);

        if (profile.isPresent())
            return profileService.getProfileInfo(profile.get(), account);
        else
            return new ProfileDTO();
    }

    @PostMapping("/grade/save")
    public void saveGrade(@RequestBody RatingDTO rating) {
        GradeDTO grade = rating.getGrade();

        System.out.println(grade.getCurrentGrade());
    }
}
