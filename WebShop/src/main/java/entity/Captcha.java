package entity;

import container.CaptchaParameterContainer;
import util.IdGenerator;

import java.util.Objects;

public class Captcha {
    private final int id;
    private final String numbers;
    private final long expirationTime;

    private Captcha(CaptchaBuilder builder) {
        id = IdGenerator.getIdCaptcha();
        numbers = builder.numbers;
        expirationTime = builder.expirationTime;
    }

    public int getId() {
        return id;
    }

    public String getNumbers() {
        return numbers;
    }

    public boolean isValid() {
        return System.currentTimeMillis() - expirationTime <=
                CaptchaParameterContainer.CAPTCHA_LIVE_TIME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Captcha captcha = (Captcha) o;
        return id == captcha.id &&
                expirationTime == captcha.expirationTime &&
                Objects.equals(numbers, captcha.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numbers, expirationTime);
    }

    public static class CaptchaBuilder {

        private String numbers;
        private long expirationTime;

        public CaptchaBuilder setNumbers(String numbers) {
            this.numbers = numbers;
            return this;
        }

        public CaptchaBuilder setExpirationTime(long expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public Captcha build() {
            return new Captcha(this);
        }
    }
}
