package dao.impl;

import constant.Constant;
import dao.UserDao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String INSERT_NEW_USER = "insert into users values (?, ?, ?, ?)";
    private static final String GET_USER_BY_LOGIN = "select * from users where userName=?";

    @Override
    public boolean isUserExist(Connection connection, String name) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return false;
    }

    @Override
    public void add(Connection connection, User user) {
        int k = 1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)) {
            statement.setString(k++, user.getEmail());
            statement.setString(k++, user.getName());
            statement.setString(k++, user.getPassword());
            statement.setString(k, user.getImage());
            statement.executeUpdate();
            System.out.println("created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromQueue(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder().setEmail(resultSet.getString(Constant.EMAIL))
                .setName(resultSet.getString(Constant.NAME))
                .setPassword(resultSet.getString(Constant.PASSWORD))
                .setImage(resultSet.getString(Constant.AVATAR))
                .build();
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserIfExistByPassword(password, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return Optional.empty();
    }

    private Optional<User> getUserIfExistByPassword(String password, ResultSet resultSet) throws SQLException {
        Optional<User> user = Optional.ofNullable(getUserFromQueue(resultSet));
        if (user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }


    private void resultSetClose(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
