package service;

import captcha.CaptchaHandler;
import creator.CaptchaCreator;
import entity.Captcha;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

public interface CaptchaService {

    CaptchaCreator create();

    BufferedImage bufferedImage(CaptchaCreator captchaCreator) throws NoSuchAttributeException;

    Captcha createCaptcha(String captchaNumbers);

    void removeOldCaptcha();

    boolean checkCaptchaOnValid(HttpServletRequest request, CaptchaHandler handler);
}
