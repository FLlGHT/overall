package com.flight.overall.service;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.entity.*;
import com.flight.overall.mapper.EntityMapper;
import com.flight.overall.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Service
public class ProfileService {


    @Autowired
    private RatingService ratingService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EntityMapper entityMapper;


    public Optional<Profile> getProfile(String username) {
        return profileRepository.findByUsernameIgnoreCase(username);
    }

    public ProfileDTO getProfileInfo(Profile profile, Account account) {
        List<Category> categories = categoryService.findAllCategories();
        List<Rating> ratings = ratingService.getProfileRatings(profile.getId());
        List<Grade> grades = gradeService.getAccountGrades(account, profile);

        return entityMapper.toProfileDTO(profile, account, categories, ratings, grades);
    }

    public ProfileDTO getProfileContacts(Profile profile, Account account) {
        List<Profile> contacts = profile.getContacts();
        return entityMapper.toProfileContacts(profile, contacts);
    }

    public Profile saveProfile(ProfileDTO profileDTO, Account account) {
        Profile profile = profileRepository.findProfile(profileDTO.getId());
        List<Rating> ratings = ratingService.getProfileRatings(profile.getId());

        ratingService.updateRatings(account, profile, ratings, profileDTO.getRatings());
        ratingService.updateOverall(profile);

        return profileRepository.save(profile);
    }

}
