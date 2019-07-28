package servlet;

import bean.ShoppingCart;
import constant.Constant;
import entity.User;
import service.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderController extends AbstractController{
    private static final long serialVersionUID = -1782066337808445826L;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        orderService = (OrderService) context.getAttribute("orderService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(Constant.CURRENT_SHOPPING_CART);
        User user = (User) getServletContext().getAttribute(Constant.USER);
        orderService.makeOrder(shoppingCart, user);
    }
}
