package captcha.impl;

import captcha.AbstractCaptchaHandler;
import entity.Captcha;
import exception.SessionTimeOutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SessionCaptchaHandler extends AbstractCaptchaHandler {

    public SessionCaptchaHandler(Map<Integer, Captcha> captches) {
        super(captches);
    }

    @Override
    public void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        int captchaID = captcha.getId();
        captches.put(captchaID, captcha);
        request.getSession().setAttribute(CAPTCHA_ID, captchaID);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException {
        int captchaId = (int) request.getSession().getAttribute(CAPTCHA_ID);
        Captcha captcha = captches.get(captchaId);
        if (captcha.isValid()) {
            return captcha;
        }
        throw new SessionTimeOutException();
    }
}
