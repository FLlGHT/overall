package flight.overall.service;

import flight.overall.entity.Post;
import flight.overall.entity.UserData;
import flight.overall.repository.PostRepository;
import flight.overall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

/**
 * @author FLIGHT
 * @creationDate 03.05.2022
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Optional<UserData> getUserData(String username) {
        return userRepository.findUserData(username.toLowerCase());
    }

    public void addUserInfo(UserData userData, Model model) {
        List<Post> userPostList = postRepository.userPosts(userData.getId());

        model.addAttribute("userData", userData);
        model.addAttribute("posts", userPostList);
    }
}
