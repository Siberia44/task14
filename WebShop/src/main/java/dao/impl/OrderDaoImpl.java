package dao.impl;

import bean.ShoppingCart;
import dao.OrderDao;
import entity.OrderItem;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {
    public static final String CREATE_NEW_ORDER = "INSERT INTO `order`(id_account,order_status,status_description) values (?,?,?)";
    public static final String CREATE_NEW_ORDER_ITEM = "INSERT INTO order_item values (?,?)";


    @Override
    public void makeOrder(ShoppingCart shoppingCart, User currentAccount, Connection connection) {
        if (shoppingCart == null || shoppingCart.getProducts().isEmpty()) {
            throw new RuntimeException();
        }
        try{
            createOrder(currentAccount.getId(), connection);
            for(OrderItem orderItem : shoppingCart.getProducts()){
                createOrderItem(currentAccount.getId(), orderItem, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createOrderItem(int id, OrderItem orderItem, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_ORDER_ITEM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, orderItem.getProduct().getId());
            int result = preparedStatement.executeUpdate();
            if (result != 1) {
                connection.rollback();
                throw new SQLException("Can't insert row to database. Result=" + result);
            }
        }
    }

    public void createOrder(int id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_ORDER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "test");
            preparedStatement.setString(3, "test");
            int result = preparedStatement.executeUpdate();
            if (result != 1) {
                connection.rollback();
                throw new SQLException("Can't insert row to database. Result=" + result);
            }
        }
    }
}
