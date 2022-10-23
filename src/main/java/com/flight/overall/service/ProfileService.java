package com.flight.overall.service;

import com.flight.overall.entity.Profile;
import com.flight.overall.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getProfiles() {
        return profileRepository.findAllProfiles();
    }

    public Optional<Profile> getProfile(String username) {
        return profileRepository.findByUsername(username);
    }
}
