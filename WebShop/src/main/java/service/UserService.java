package service;

import creator.ImageCreator;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.Optional;

public interface UserService {
    boolean isUserPresent(String name);

    Optional<User> add(HttpServletRequest request, Part part, ImageCreator imageCreator);

    Optional<User> getUserByLoginAndPassword(String login, String password);
}
