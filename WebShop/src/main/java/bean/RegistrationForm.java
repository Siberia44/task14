package bean;

import constant.Constant;
import creator.ImageCreator;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class RegistrationForm {


    public User createUserByRequest(HttpServletRequest request, Part part, ImageCreator imageCreator) {
        User user = new User.UserBuilder().setName(request.getParameter(Constant.NAME))
                .setPassword(request.getParameter(Constant.PASSWORD))
                .setEmail(request.getParameter(Constant.EMAIL))
                .build();
        user.setImage(saveAvatar(user, part, imageCreator));
        return user;
    }

    private String saveAvatar(User user, Part part, ImageCreator imageCreator) {
        if (part != null) {
            String pathToImage = imageCreator.add(part, user.getName());
            if (pathToImage.equals(user.getName() + ".filename=")) {
                return Constant.DEFAULT_IMAGE;
            } else {
                return pathToImage;
            }
        }
        return "";
    }
}
