package service.impl;

import captcha.CaptchaHandler;
import constant.Constant;
import creator.CaptchaCreator;
import dao.CaptchaDao;
import entity.Captcha;
import exception.SessionTimeOutException;
import service.CaptchaService;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CaptchaServiceImpl implements CaptchaService {
    private CaptchaDao captchaDao;

    public CaptchaServiceImpl(CaptchaDao captchaDao) {
        this.captchaDao = captchaDao;
    }

    @Override
    public CaptchaCreator create() {
        return new CaptchaCreator().setHeight(40)
                .setWidth(220)
                .setSymbolCount(9);
    }

    @Override
    public BufferedImage bufferedImage(CaptchaCreator captchaCreator) throws NoSuchAttributeException {
        return captchaCreator.createImage();
    }

    @Override
    public void removeOldCaptcha() {
        List<Integer> removeId = new ArrayList<>();
        for (Map.Entry<Integer, Captcha> entry : captchaDao.getAllCaptches().entrySet()) {
            if (!entry.getValue().isValid()) {
                removeId.add(entry.getKey());
            }
        }
        for (Integer id : removeId) {
            captchaDao.removeCaptcha(id);
        }
    }

    @Override
    public boolean checkCaptchaOnValid(HttpServletRequest request, CaptchaHandler handler) {
        try {
            Captcha captcha = handler.getCaptcha(request);
            return captcha.getNumbers().equals(request.getParameter(Constant.CAPTCHA));
        } catch (SessionTimeOutException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Captcha createCaptcha(String captchaNumbers) {
        return new Captcha.CaptchaBuilder().setExpirationTime(new Date().getTime())
                .setNumbers(captchaNumbers).build();
    }
}