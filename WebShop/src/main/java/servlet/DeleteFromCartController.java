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

@WebServlet("/deleteFromCart")
public class DeleteFromCartController extends AbstractController {
    private static final long serialVersionUID = 8956105311512250143L;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productService.getProductById(Integer.parseInt(request.getParameter("id")));
        ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(Constant.CURRENT_SHOPPING_CART);
        shoppingCart.removeProduct(product.getId());
        shoppingCart.getProducts().clear();
        forwardToPage("index.jsp", request, response);
    }
}
