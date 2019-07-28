package dao;

import entity.Captcha;

import java.util.Map;

public interface CaptchaDao {
    Captcha getCaptcha(int key);

    void removeCaptcha(int key);

    Map<Integer, Captcha> getAllCaptches();
}
