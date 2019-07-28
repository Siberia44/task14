package service.impl;

import bean.SQLBuilder;
import bean.SearchForm;
import dao.ProductDao;
import dao.transaction.TransactionManager;
import entity.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private TransactionManager transactionManager;

    public ProductServiceImpl(ProductDao productDao, TransactionManager transactionManager) {
        this.productDao = productDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<String> getAllCountries() {
        return transactionManager.doInTransaction(connection -> productDao.getAllCountries(connection));
    }

    @Override
    public List<String> getAllTypes() {
        return transactionManager.doInTransaction(connection -> productDao.getAllTypes(connection));
    }

    @Override
    public int getCountOfProducts(SearchForm searchForm) {
        if (searchForm == null) {
            return transactionManager.doInTransaction(connection -> productDao.getCountOfProducts(connection));
        } else {
            return getCountOfProductsForSearch(searchForm);
        }
    }

    @Override
    public List<Product> getListOfProducts(SearchForm searchForm, int limit, int offset) {
        if (searchForm == null) {
            return getAllProducts(limit, offset);
        } else {
            return getListProductBySearchForm(searchForm, limit, offset);
        }
    }

    @Override
    public Product getProductById(int id) {
        return transactionManager.doInTransaction(connection -> productDao.getProductById(connection, id));
    }

    private int getCountOfProductsForSearch(SearchForm searchForm) {
        SQLBuilder sqlBuilder = new SQLBuilder(searchForm);
        StringBuilder sqlRequest = sqlBuilder.createLine();
        List<String> listOfParameters = sqlBuilder.getParams();
        return transactionManager.doInTransaction(connection -> productDao.getListProductsBySearchForm(connection, String.valueOf(sqlRequest), listOfParameters).size());
    }

    private List<Product> getListProductBySearchForm(SearchForm searchForm, int limit, int offset) {
        SQLBuilder sqlBuilder = new SQLBuilder(searchForm);
        StringBuilder sqlRequest = sqlBuilder.createLine();
        sqlRequest.append(" LIMIT " + limit).append(" OFFSET " + offset);
        List<String> listOfParameters = sqlBuilder.getParams();
        return transactionManager.doInTransaction(connection -> productDao.getListProductsBySearchForm(connection, String.valueOf(sqlRequest), listOfParameters));
    }

    private List<Product> getAllProducts(int limit, int offset) {
        return transactionManager.doInTransaction(connection -> productDao.getAllProducts(connection, limit, offset));
    }
}
