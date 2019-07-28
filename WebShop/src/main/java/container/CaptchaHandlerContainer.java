package container;

import captcha.CaptchaHandler;
import captcha.impl.CookieCaptchaHandler;
import captcha.impl.HiddenFieldCaptchaHandler;
import captcha.impl.SessionCaptchaHandler;
import constant.Constant;
import entity.Captcha;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CaptchaHandlerContainer {

    private final Map<Integer, Captcha> captches = new LinkedHashMap<>();
    private Map<String, CaptchaHandler> handlers = new HashMap<>();

    public CaptchaHandlerContainer() {
        handlersInit();
    }

    private void handlersInit() {
        handlers.put(Constant.HIDDEN_FIELD_CAPTCHA_HANDLER, new HiddenFieldCaptchaHandler(captches));
        handlers.put(Constant.COOKIE_CAPTCHA_HANDLER, new CookieCaptchaHandler(captches));
        handlers.put(Constant.SESSION_CAPTCHA_HANDLER, new SessionCaptchaHandler(captches));
    }

    public CaptchaHandler getCaptchaHandler(String handlerName) {
        return handlers.get(handlerName);
    }
}
