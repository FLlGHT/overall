package com.flight.overall.service;

import com.flight.overall.dto.AccountDTO;
import com.flight.overall.dto.ExternalLinkDTO;
import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.SettingsDTO;
import com.flight.overall.entity.*;
import com.flight.overall.mapper.EntityMapper;
import com.flight.overall.repository.AccountRepository;
import com.flight.overall.repository.ExternalLinkRepository;
import com.flight.overall.repository.ProfileRepository;
import com.flight.overall.repository.SettingsRepository;
import com.flight.overall.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author FLIGHT
 * @date 29.10.2022
 */

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ExternalLinkRepository externalLinkRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private EntityMapper mapper;


    public SettingsDTO getSettings(Account account) {
        Settings settings = account.getSettings();
        return mapper.toSettingsDTO(settings, account);
    }

    public void saveSettings(Account account, SettingsDTO settingsDTO) {
        Settings settings = settingsRepository.findById(settingsDTO.getId()).orElse(new Settings());

        settings.setProfileClosed(settingsDTO.isClosedProfile());
        settings.setGradesClosed(settingsDTO.isClosedGrades());

        settingsRepository.save(settings);
        saveSettings(account, settings, settingsDTO);
    }

    private void saveSettings(Account account, Settings settings, SettingsDTO settingsDTO) {
        ProfileDTO profileDTO = settingsDTO.getProfile();
        AccountDTO accountDTO = settingsDTO.getAccount();
        Profile profile = account.getProfile();

        Optional<Image> newImage = imageService.uploadImage(profileDTO.getImage());

        profile.setFirstName(profileDTO.getFirstName());
        profile.setSecondName(profileDTO.getSecondName());
        profile.setUsername(accountDTO.getUsername());
        profile.setDateOfBirth(profileDTO.getDateOfBirth());
        profile.setDescription(profileDTO.getDescription());
        profile.setEmail(profileDTO.getEmail());
        profile.setPlaceOfResidence(profileDTO.getPlaceOfResidence());
        newImage.ifPresent(profile::setProfileImage);
        saveExternalLinks(profileDTO.getExternalLinks(), profile);

        account.setUsername(accountDTO.getUsername());
        account.setSettings(settings);

        profileRepository.save(profile);
        accountRepository.save(account);
    }

    private void saveExternalLinks(List<ExternalLinkDTO> externalLinks, Profile profile) {
        for (ExternalLinkDTO externalLinkDTO : externalLinks) {
            ExternalLink externalLink = externalLinkRepository.findById(externalLinkDTO.getId()).orElse(new ExternalLink());
            boolean linkIsEmpty = externalLinkDTO.getLink().equals("");

            if (externalLinkDTO.getId() > 0 && linkIsEmpty)
                externalLinkRepository.delete(externalLink);
            else if (!linkIsEmpty) {
                externalLink.setLink(externalLinkDTO.getLink());
                externalLink.setProfile(profile);
                externalLinkRepository.save(externalLink);
            }
        }
    }
}
