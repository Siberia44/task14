package dao;

import entity.User;

import java.sql.Connection;
import java.util.Optional;

public interface UserDao {
    boolean isUserExist(Connection connection, String name);

    void add(Connection connection, User user);

    Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password);
}
