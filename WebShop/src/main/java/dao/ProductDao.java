package dao;

import entity.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts(Connection connection, int limit, int offset);

    List<String> getAllCountries(Connection connection);

    List<String> getAllTypes(Connection connection);

    List<Product> getListProductsBySearchForm(Connection connection, String sqlRequest, List<String> listOfParameters);

    int getCountOfProducts(Connection connection);

    Product getProductById(Connection connection, int id);
}
