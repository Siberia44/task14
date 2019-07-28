package captcha;

import dao.CaptchaDao;
import dao.impl.CaptchaDaoImpl;
import entity.Captcha;
import exception.SessionTimeOutException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.impl.CaptchaServiceImpl;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCaptchaServiceTest {

    private static final String CAPTCHA_NUMBERS = "666";
    private CaptchaDao dao = new CaptchaDaoImpl();

    @Mock
    private HttpServletRequest request;

    @Mock
    private Captcha captcha;

    @Mock
    private CaptchaHandler captchaHandler;

    @InjectMocks
    private CaptchaServiceImpl captchaService;

    @Before
    public void setUp() {
        captchaService = new CaptchaServiceImpl(dao);
    }

    @Test
    public void createCaptcha() throws NoSuchAttributeException {
        captchaService.create();
    }

    @Test
    public void checkCaptchaOnValidAndReturnTrueWhenCaptchaIsValid() throws SessionTimeOutException {
        Mockito.when(captchaHandler.getCaptcha(request)).thenReturn(captcha);
        Mockito.when(captcha.getNumbers()).thenReturn(CAPTCHA_NUMBERS);
        Mockito.when(request.getParameter(anyString())).thenReturn(CAPTCHA_NUMBERS);
        assertTrue(captchaService.checkCaptchaOnValid(request, captchaHandler));
    }

    @Test
    public void checkCaptchaOnValidAndReturnFalseWhenCaptchaIsNotValid() throws SessionTimeOutException {
        Mockito.when(captchaHandler.getCaptcha(request)).thenThrow(SessionTimeOutException.class);
        assertFalse(captchaService.checkCaptchaOnValid(request, captchaHandler));
    }

}

