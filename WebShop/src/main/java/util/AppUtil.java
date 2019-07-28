package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtil {
    private AppUtil() {
    }

    public static void loadProperties(Properties props, String classPathUrl) {
        try (InputStream in = AppUtil.class.getClassLoader().getResourceAsStream(classPathUrl)) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't load properties from classpath:" + classPathUrl, e);
        }
    }
}
