package service;

import bean.SearchForm;
import entity.Product;

import java.util.List;

public interface ProductService {
    List<String> getAllCountries();

    List<String> getAllTypes();

    int getCountOfProducts(SearchForm searchForm);

    List<Product> getListOfProducts(SearchForm searchForm, int limit, int offset);

    Product getProductById(int id);
}
