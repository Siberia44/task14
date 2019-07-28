package filter;

import bean.ShoppingCart;
import constant.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;


@WebFilter("/*")
public class ShoppingCartFilter implements Filter, Serializable {

    private static final long serialVersionUID = -4691121295725387264L;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(Constant.CURRENT_SHOPPING_CART);
        if (shoppingCart == null) {
            session.setAttribute(Constant.CURRENT_SHOPPING_CART, new ShoppingCart());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
