package servlet;

import bean.SearchForm;
import constant.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/search")
public class SearchController extends AbstractController implements Serializable {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchForm searchForm = createSearchForm(req);
        req.getSession().setAttribute(Constant.SEARCH_FORM, searchForm);
        resp.sendRedirect(Constant.INDEX);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(Constant.SEARCH_FORM, null);
        resp.sendRedirect(Constant.INDEX);
    }
}
