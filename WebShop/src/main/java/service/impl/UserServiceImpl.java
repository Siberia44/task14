package service.impl;

import bean.RegistrationForm;
import creator.ImageCreator;
import dao.UserDao;
import dao.transaction.TransactionManager;
import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean isUserPresent(String name) {
        return transactionManager.doInTransaction(connection ->
                userDao.isUserExist(connection, name));
    }

    @Override
    public Optional<User> add(HttpServletRequest request, Part part, ImageCreator imageCreator) {
        User user = new RegistrationForm().createUserByRequest(request, part, imageCreator);
        transactionManager.doInTransaction(connection -> {
            userDao.add(connection, user);
            return null;
        });
        return Optional.of(user);
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        return transactionManager.doInTransaction(connection ->
                userDao.getUserByLoginAndPassword(connection, login, password));
    }
}
