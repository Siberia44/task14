package dao;

import bean.ShoppingCart;
import entity.User;

import java.sql.Connection;

public interface OrderDao {
    void makeOrder(ShoppingCart shoppingCart, User currentAccount, Connection connection);

}
