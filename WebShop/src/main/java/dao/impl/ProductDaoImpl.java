package dao.impl;

import dao.ProductDao;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final static String GET_ALL_TYPES = "SELECT DISTINCT productType from Products ORDER BY productType";
    private final static String GET_ALL_COUNTRIES = "SELECT DISTINCT productCountry from Products ORDER BY productCountry";
    private final static String GET_PRODUCTS_FOR_ONE_PAGE = "SELECT * FROM Products limit ? offset ?";
    private final static String GET_PRODUCTS_BY_ID = "SELECT * FROM Products WHERE productId = ?";

    @Override
    public List<Product> getAllProducts(Connection connection, int limit, int offset) {
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_FOR_ONE_PAGE)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return products;
    }

    @Override
    public List<String> getAllCountries(Connection connection) {
        ResultSet resultSet = null;
        List<String> countries = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_COUNTRIES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countries.add(resultSet.getString("productCountry"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return countries;
    }

    @Override
    public List<String> getAllTypes(Connection connection) {
        ResultSet resultSet = null;
        List<String> types = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TYPES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                types.add(resultSet.getString("productType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return types;
    }

    @Override
    public List<Product> getListProductsBySearchForm(Connection connection, String sqlRequest, List<String> listOfParameters) {
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            for (int i = 0; i < listOfParameters.size(); i++) {
                statement.setString(i+1, listOfParameters.get(i));
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return products;
    }

    @Override
    public int getCountOfProducts(Connection connection) {
        ResultSet resultSet = null;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) as count FROM Products")) {
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return result;
    }

    @Override
    public Product getProductById(Connection connection, int id) {
        ResultSet resultSet = null;
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = getProductFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return product;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("productId"));
        product.setProductType(resultSet.getString("productType"));
        product.setProductName(resultSet.getString("productName"));
        product.setProductInfo(resultSet.getString("productInfo"));
        product.setProductImg(resultSet.getString("productImg"));
        product.setProductCost(resultSet.getInt("productCost"));
        product.setProductCountry(resultSet.getString("productCountry"));
        return product;
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
