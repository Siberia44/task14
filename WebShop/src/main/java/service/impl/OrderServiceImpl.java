package service.impl;

import bean.ShoppingCart;
import dao.OrderDao;
import entity.User;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void makeOrder(ShoppingCart shoppingCart, User user) {

    }
}
