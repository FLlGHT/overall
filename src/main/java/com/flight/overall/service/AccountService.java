package com.flight.overall.service;

import com.flight.overall.dto.AccountDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Profile;
import com.flight.overall.entity.Role;
import com.flight.overall.exception.ProfileAlreadyExistException;
import com.flight.overall.repository.AccountRepository;
import com.flight.overall.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RatingService ratingService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }

    public void registerAndAuthenticate(AccountDTO account) {
        Account registered = registerNewAccount(account);
        authenticate(registered);
    }

    public Account registerNewAccount(AccountDTO accountDTO) throws ProfileAlreadyExistException {
        if (accountExists(accountDTO))
            throw new ProfileAlreadyExistException(accountDTO.getUsername());

        Profile profile = createNewProfile(accountDTO);
        return createNewAccount(accountDTO, profile);
    }

    private Profile createNewProfile(AccountDTO accountDTO) {
        Profile profile = new Profile();

        profile.setFirstName(accountDTO.getFirstName());
        profile.setSecondName(accountDTO.getSecondName());

        profile.setUsername(accountDTO.getUsername());
        profile.setOverallRating(0);

        profileRepository.save(profile);

        return profile;
    }

    private Account createNewAccount(AccountDTO accountDTO, Profile profile) {
        Account account = new Account();

        account.setProfile(profile);
        account.setUsername(accountDTO.getUsername());
        account.setPassword(getPasswordEncoder().encode(accountDTO.getPassword()));
        account.setRole(Role.USER);

        return accountRepository.save(account);
    }

    public void authenticate(Account account) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(account, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean accountExists(AccountDTO accountDTO) {
        Optional<Profile> profile = profileRepository.findByUsernameIgnoreCase(accountDTO.getUsername());
        return profile.isPresent();
    }

    private PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
