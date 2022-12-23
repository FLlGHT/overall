package com.flight.overall.controller;

import com.flight.overall.dto.ResponseEntity;
import com.flight.overall.dto.SettingsDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.service.ImageService;
import com.flight.overall.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SettingsRestController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/settings")
    public SettingsDTO openSettings(Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();

        if (account != null)
            return settingsService.getSettings(account);
        else
            return new SettingsDTO();
    }

    @PostMapping("/settings/save")
    public ResponseEntity saveSettings(Authentication authentication,
                                       @RequestBody SettingsDTO settings) {
        Account account = (Account) authentication.getPrincipal();

        settingsService.saveSettings(account, settings);
        return new ResponseEntity("Settings saved successfully");
    }

    @PostMapping("/image/save")
    public ResponseEntity saveImage(Authentication authentication,
                                    @RequestParam("image") MultipartFile image) {
        Account account = (Account) authentication.getPrincipal();

        imageService.saveProfileImage(image, account.getProfile());
        return new ResponseEntity("Image saved successfully");
    }
}
