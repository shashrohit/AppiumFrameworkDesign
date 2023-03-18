package TestUtils;

import com.shashank.android.poms.login.impl.LogInPage;
import com.shashank.android.utils.AppiumUtils;
import com.shashank.android.utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected AppiumDriverLocalService service;
    private AndroidDriver driver;
    protected LogInPage logInPage;
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUp(){
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        log.info("TEST Suite has started");
    }
    @BeforeClass(alwaysRun = true)
    public void configureAppiumServer() throws IOException {

        String ip = AppiumUtils.getProperty(Constants.PROPERTY_FILE_PATH, "ipAddress");
        int port = Integer.parseInt(AppiumUtils.getProperty(Constants.PROPERTY_FILE_PATH, "port"));
        String deviceName = AppiumUtils.getProperty(Constants.PROPERTY_FILE_PATH, "androidDeviceName");

        service = AppiumUtils.startAppiumService(ip, port);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setChromedriverExecutable(Constants.CHROME_DRIVER_EXECUTABLE_PATH);
        options.setCapability("browser", "chrome");
        options.setApp(Constants.APK_PATH);

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logInPage = new LogInPage(driver);
    }

    @AfterClass
    public void teardown(){
        driver.quit();
//        service.stop();
    }

    @AfterSuite
    public void tearDown(){
        log.info("TEST Suite has finished");
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # #");
    }
}
