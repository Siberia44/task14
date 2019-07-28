package servlet;

import bean.ShoppingCart;
import constant.Constant;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/shopping-cart")
public class ShoppingCartController extends AbstractController{
    private static final long serialVersionUID = -1916373553298888514L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage("shopping-cart.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(Constant.CURRENT_SHOPPING_CART);
        int id = Integer.parseInt(request.getParameter("id"));
        int countProduct = Integer.parseInt(request.getParameter("count"));
        int price = cart.update(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("itemPrice", price);
        jsonObject.put("totalPrice", cart.getTotalCost().multiply(BigDecimal.valueOf(countProduct)));
        sendJSON(jsonObject, response);

    }
}
