package flight.overall.service;

import flight.overall.entity.UserData;
import flight.overall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

/**
 * @author FLIGHT
 * @creationDate 03.05.2022
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserData> getUserData(String username) {
        return userRepository.findUserData(username.toLowerCase());
    }

    public void addAttributesToUser(UserData userData, Model model) {
        model.addAttribute("userData", userData);
    }
}
