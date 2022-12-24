package com.flight.overall.service;

import com.flight.overall.dto.*;
import com.flight.overall.entity.*;
import com.flight.overall.mapper.EntityMapper;
import com.flight.overall.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
    private CompanyRepository companyRepository;
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
        saveProfileSettings(account, settings, settingsDTO);
    }

    private void saveProfileSettings(Account account, Settings settings, SettingsDTO settingsDTO) {
        Profile profile = account.getProfile();

        updateProfileFields(settingsDTO.getProfile(), settingsDTO.getAccount(), profile);
        updateAccountFields(account, settings, settingsDTO.getAccount());
    }

    private void updateAccountFields(Account account, Settings settings, AccountDTO accountDTO) {
        account.setUsername(accountDTO.getUsername());
        account.setSettings(settings);

        accountRepository.save(account);
    }

    private void updateProfileFields(ProfileDTO profileDTO, AccountDTO accountDTO, Profile profile) {

        profile.setFirstName(profileDTO.getFirstName());
        profile.setSecondName(profileDTO.getLastName());
        profile.setUsername(accountDTO.getUsername());
        profile.setDateOfBirth(profileDTO.getDateOfBirth());
        profile.setDescription(profileDTO.getDescription());
        profile.setEmail(profileDTO.getEmail());
        profile.setPlaceOfResidence(profileDTO.getPlaceOfResidence());
        saveExternalLinks(profileDTO.getExternalLinks(), profile);
        saveCompany(profileDTO, profile);


        profileRepository.save(profile);
    }

    private void saveExternalLinks(List<ExternalLinkDTO> externalLinks, Profile profile) {
        for (ExternalLinkDTO externalLinkDTO : externalLinks) {
            ExternalLink externalLink = externalLinkRepository.findById(externalLinkDTO.getId()).orElse(new ExternalLink());

            externalLink.setLink(externalLinkDTO.getLink());
            externalLink.setTitle(toTitle(externalLinkDTO.getLink()));
            externalLink.setProfile(profile);
            externalLinkRepository.save(externalLink);
        }
    }

    private String toTitle(String link) {
        if (link == null || link.isEmpty())
            return null;

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(link).build();
        String host = uriComponents.getHost();
        return host == null ? null : (host.startsWith("www.") ? host.substring(4) : host);
    }

    private void saveCompany(ProfileDTO profileDTO, Profile profile) {
        CompanyDTO companyDTO = profileDTO.getCompany();

        if (companyDTO != null && !companyDTO.getName().isEmpty()) {
            Company company = companyRepository.findById(companyDTO.getId()).orElse(new Company());

            company.setName(companyDTO.getName());
            company.setDescription(companyDTO.getDescription());

            companyRepository.save(company);
            profile.setCompany(company);
            profile.setRole(profileDTO.getRole());
        }
    }
}
