package com.flight.overall.service;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.entity.Profile;
import com.flight.overall.entity.Rating;
import com.flight.overall.mapper.ProfileMapper;
import com.flight.overall.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private RatingService ratingService;

    @Autowired
    private ProfileMapper profileMapper;

    public Optional<Profile> getProfile(String username) {
        return profileRepository.findByUsername(username);
    }


    public String getProfile(String username, Model model) {
        Optional<Profile> optional = getProfile(username);

        if (optional.isPresent()) {
            Profile profile = optional.get();
            List<Rating> ratings = ratingService.getProfileRatings(profile.getId());

            model.addAttribute("profile", profileMapper.toProfileDTO(profile, ratings));

            return "profile";
        }

        return "error";
    }

    public String saveProfile(ProfileDTO profileDTO, Model model) {
        Profile profile = profileRepository.findProfile(profileDTO.getId());
        List<Rating> ratings = ratingService.getProfileRatings(profile.getId());

        ratingService.updateRatings(ratings, profileDTO.getRatings());
        ratingService.updateOverall(profile);

        profileRepository.save(profile);
        return "redirect:/" + profile.getUsername();
    }

}
