package TestUtils;

import com.shashank.android.poms.login.impl.LogInPage;
import com.shashank.android.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.time.Duration;

public class BaseTest extends AppiumUtils {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;
    public LogInPage logInPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppiumServer() throws IOException {

        String userDir = System.getProperty("user.dir");
        String propertyFilePath = userDir + "/src/main/java/resources/data.properties";
        String ip = getProperty(propertyFilePath, "ipAddress");
        int port = Integer.parseInt(getProperty(propertyFilePath, "port"));
        String deviceName = getProperty(propertyFilePath, "androidDeviceName");

        service = startAppiumService(ip, port);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setChromedriverExecutable(userDir + "\\src\\main\\java\\resources\\chromedriver.exe");
        options.setApp(userDir + "\\src\\main\\java\\resources\\store.apk");

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logInPage = new LogInPage(driver);
    }

    @AfterClass
    public void teardown(){
        driver.quit();
//        service.stop();
    }
}
