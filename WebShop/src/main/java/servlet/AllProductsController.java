package servlet;

import bean.SearchForm;
import constant.Constant;
import entity.Product;
import service.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class AllProductsController extends AbstractController {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        productService = (ProductService) servletContext.getAttribute(Constant.PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchForm searchForm = (SearchForm) req.getSession().getAttribute(Constant.SEARCH_FORM);
        checkInfoForPagination(req);
        int limit = (int) req.getSession().getAttribute(Constant.MAX_COUNT_OF_ITEM);
        int offset = getOffset(req) * limit;
        List<Product> products = productService.getListOfProducts(searchForm, limit, offset);
        req.setAttribute(Constant.ALL_PRODUCTS, products);
        req.setAttribute(Constant.PRODUCT_COUNT, productService.getCountOfProducts(searchForm));
        forwardToPage(Constant.PRODUCT_JSP, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String count = req.getParameter(Constant.COUNT);
        req.getSession().setAttribute(Constant.MAX_COUNT_OF_ITEM, Integer.parseInt(count));
        doGet(req, resp);
    }

    private int getOffset(HttpServletRequest req) {
        String page = req.getParameter(Constant.PAGE);
        if (page == null) {
            return 0;
        }
        return Integer.parseInt(page);
    }

    private void checkInfoForPagination(HttpServletRequest req) {
        if (req.getSession().getAttribute(Constant.MAX_COUNT_OF_ITEM) == null) {
            req.getSession().setAttribute(Constant.MAX_COUNT_OF_ITEM, Constant.DEFAULT_COUNT);
        }
    }
}