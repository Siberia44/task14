package listener;

import captcha.CaptchaHandler;
import constant.Constant;
import container.CaptchaHandlerContainer;
import creator.ImageCreator;
import dao.CaptchaDao;
import dao.OrderDao;
import dao.UserDao;
import dao.ProductDao;
import dao.impl.CaptchaDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
import dao.transaction.TransactionManager;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import service.CaptchaService;
import service.OrderService;
import service.UserService;
import service.ProductService;
import service.impl.CaptchaServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
import util.AppUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.nio.file.Paths;
import java.util.Properties;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private TransactionManager transactionManager;
    private UserDao userDao;
    private CaptchaDao captchaDao;
    private CaptchaService captchaService;
    private UserService userService;
    private BasicDataSource dataSource = new BasicDataSource();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        connectionPoolInit();
        transactionInit();
        ProductDao productDao = new ProductDaoImpl();
        ProductService productService = new ProductServiceImpl(productDao, transactionManager);
        createDao();
        createServices();
        OrderDao orderDao = new OrderDaoImpl();
        OrderService orderService = new OrderServiceImpl(orderDao);
        setCaptchaHandler(sce);
        sce.getServletContext().setAttribute(Constant.USER_SERVICE, userService);
        sce.getServletContext().setAttribute(Constant.CAPTCHA_SERVICE, captchaService);
        sce.getServletContext().setAttribute("productService", productService);
        sce.getServletContext().setAttribute("orderService", orderService);
        String imagePath = sce.getServletContext().getInitParameter("avatarPath");
        ImageCreator image = new ImageCreator(Paths.get(imagePath));
        sce.getServletContext().setAttribute(Constant.AVATAR, image);
        sce.getServletContext().setAttribute("country", productService.getAllCountries());
        sce.getServletContext().setAttribute("type", productService.getAllTypes());
    }

    private void transactionInit() {
        transactionManager = new TransactionManager(dataSource);
    }

    private void connectionPoolInit() {
        Properties properties = getConnectionPoolProperties();
        dataSource.setDefaultAutoCommit(Constant.DEFAULT_AUTO_COMMIT);
        dataSource.setRollbackOnReturn(Constant.ROLLBACK_ON_RETURN);
        dataSource.setDriverClassName(properties.getProperty(Constant.DRIVER));
        dataSource.setUrl(properties.getProperty(Constant.URL));
        dataSource.setUsername(properties.getProperty(Constant.USER_NAME));
        dataSource.setPassword(properties.getProperty(Constant.DB_PASSWORD));
        dataSource.setInitialSize(Integer.parseInt(properties.getProperty(Constant.INIT_SIZE)));
        dataSource.setMaxTotal(Integer.parseInt(properties.getProperty(Constant.MAX_SIZE)));
    }

    private Properties getConnectionPoolProperties() {
        Properties properties = new Properties();
        AppUtil.loadProperties(properties, Constant.CONNECTION_POOL_PROPERSTIES_FILE);
        return properties;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void createDao() {
        userDao = new UserDaoImpl();
        captchaDao = new CaptchaDaoImpl();
    }

    private void createServices() {
        captchaService = new CaptchaServiceImpl(captchaDao);
        userService = new UserServiceImpl(userDao, transactionManager);
    }

    private void setCaptchaHandler(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String handlerName = context.getInitParameter(Constant.CAPTCHA_HANDLER);
        CaptchaHandler handler = new CaptchaHandlerContainer().getCaptchaHandler(handlerName);
        context.setAttribute(Constant.CAPTCHA_PRESERVER, handler);
    }
}
