package servlet;

import captcha.CaptchaHandler;
import constant.Constant;
import creator.CaptchaCreator;
import creator.ImageCreator;
import entity.Captcha;
import entity.User;
import sender.CaptchaSender;
import service.CaptchaService;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/check-login")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegistrationServlet extends HttpServlet {
    private UserService userService;
    private CaptchaService captchaService;
    private CaptchaHandler captchaHandler;
    private CaptchaCreator captchaCreator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute(Constant.USER_SERVICE);
        captchaService = (CaptchaService) context.getAttribute(Constant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) context.getAttribute(Constant.CAPTCHA_PRESERVER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CaptchaSender sender = new CaptchaSender(req);
        captchaCreator = captchaService.create();
        captchaCreator.createCaptchaNumbers();
        Captcha captcha = captchaService.createCaptcha(captchaCreator.getCaptchaNumbers());
        sender.setCaptchaId(captcha.getId());
        captchaHandler.addCaptcha(req, resp, captcha);
        HttpSession session = req.getSession();
        session.setAttribute("captchaCreator", captchaCreator);
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = tryGetPart(req);
        String name = req.getParameter(Constant.NAME);
        if (userService.isUserPresent(name) || !captchaService.checkCaptchaOnValid(req, captchaHandler)) {
            saveAllInformation(req);
            req.getRequestDispatcher(Constant.REGISTRATION_JSP).forward(req, resp);
        } else {
            req.setAttribute(Constant.NAME_FOR_ATTRIBUTE, req.getParameter(Constant.NAME));
            saveAllInformation(req);
            createUser(req, part);
            resp.sendRedirect(Constant.MAIN_PAGE);
        }
    }

    private void saveAllInformation(HttpServletRequest req) {
        req.setAttribute(Constant.SURNAME_FOR_ATTRIBUTE, req.getParameter(Constant.PASSWORD));
        req.setAttribute(Constant.EMAIL_FOR_ATTRIBUTE, req.getParameter(Constant.EMAIL));
    }

    private void createUser(HttpServletRequest req, Part part) {
        ImageCreator imageCreator = (ImageCreator) getServletContext().getAttribute(Constant.AVATAR);
        Optional<User> user = userService.add(req, part, imageCreator);
        HttpSession session = req.getSession();
        session.setAttribute(Constant.USER, user.get());
    }

    private Part tryGetPart(HttpServletRequest req) {
        Part part = null;
        try {
            part = req.getPart(Constant.AVATAR);
        } catch (IOException | ServletException e) {
            System.out.println(e);
        }
        return part;
    }
}