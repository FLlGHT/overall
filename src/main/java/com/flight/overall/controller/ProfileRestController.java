package com.flight.overall.controller;


import com.flight.overall.dto.GradeDTO;
import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Profile;
import com.flight.overall.repository.AccountRepository;
import com.flight.overall.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProfileRestController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{username}")
    public ProfileDTO getProfile(@PathVariable String username) {
        Optional<Profile> profile = profileService.getProfile(username);
        Account account = accountRepository.findByUsername("flight");

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
