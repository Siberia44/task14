package servlet;

import bean.ShoppingCart;
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

@WebServlet("/addToCart")
public class AddToShoppingCartController extends AbstractController {
    private static final long serialVersionUID = 143951577571711026L;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = productService.getProductById(Integer.parseInt(req.getParameter("id")));
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(Constant.CURRENT_SHOPPING_CART);
        shoppingCart.addProduct(product);
    }
}
