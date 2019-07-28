package container;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserContainer {
    private User user1;
    private User user2;
    private User user3;

    private List<User> usersList = new ArrayList<User>();

    public UserContainer() {
        fillingUsers();
        fillingList();
    }

    public List<User> getUsersList() {
        return usersList;
    }

    private void fillingList() {
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
    }

    private void fillingUsers() {
        user1 = new User.UserBuilder()
                .setName("user1")
                .setPassword("password1")
                .setEmail("email1@email.com")
                .build();

        user2 = new User.UserBuilder()
                .setName("user2")
                .setPassword("password2")
                .setEmail("email2@email.com")
                .build();

        user3 = new User.UserBuilder()
                .setName("user3")
                .setPassword("password3")
                .setEmail("email3@email.com")
                .build();
    }
}
