package service;

import bean.ShoppingCart;
import entity.User;

public interface OrderService {
    void makeOrder(ShoppingCart shoppingCart, User user);
}
