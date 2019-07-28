package servlet;

import constant.Constant;
import creator.CaptchaCreator;
import sender.CaptchaSender;
import service.CaptchaService;

import javax.imageio.ImageIO;
import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha")
public class CaptchaController extends HttpServlet {
    private CaptchaService captchaService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        captchaService = (CaptchaService) context.getAttribute(Constant.CAPTCHA_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        captchaService.removeOldCaptcha();
        getSenderWithNewCaptcha(req, resp);
    }

    private void getSenderWithNewCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            CaptchaCreator captchaCreator = (CaptchaCreator) session.getAttribute("captchaCreator");
            BufferedImage bufferedImage = captchaService.bufferedImage(captchaCreator);
            OutputStream osImage = response.getOutputStream();
            new CaptchaSender(request).send();
            ImageIO.write(bufferedImage, "jpeg", osImage);
        } catch (NoSuchAttributeException | IOException e) {
            e.printStackTrace();
        }
    }
}
