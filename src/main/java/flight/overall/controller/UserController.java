package flight.overall.controller;

import flight.overall.entity.UserData;
import flight.overall.service.PostService;
import flight.overall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author FLIGHT
 * @creationDate 03.05.2022
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/{username}")
    public String getUserInfo(@PathVariable String username, Model model) {

        Optional<UserData> userData = userService.getUserData(username);
        userData.ifPresent(data -> userService.addUserInfo(data, model));

        return "userInfo";
    }
}
