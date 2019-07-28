package servlet;

import bean.SearchForm;
import constant.Constant;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Constant.CURRENT_PAGE, jspPage);
        req.getRequestDispatcher(Constant.MAIN_PAGE).forward(req, resp);
    }

    protected final SearchForm createSearchForm(HttpServletRequest request) {
        return new SearchForm(request.getParameter("searchByName"),
                request.getParameterValues("type"),
                request.getParameterValues("countries"),
                request.getParameter("minPrice"),
                request.getParameter("maxPrice")
        );
    }

    static void sendJSON(JSONObject object, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().println(object.toString());
        resp.getWriter().close();
    }
}
