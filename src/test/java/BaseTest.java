import com.shashank.android.poms.LogInPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    AppiumDriverLocalService service;
    AndroidDriver driver;
    LogInPage logInPage;

    @BeforeClass
    public void configureAppiumServer() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\DS\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
//        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 2 XL Android 12.0 Shashank");
        options.setChromedriverExecutable("C:\\Users\\DS\\Desktop\\Projects\\MobileAutomationDemo\\src\\main\\java\\resources\\chromedriver.exe");
        options.setApp("C:\\Users\\DS\\Desktop\\Projects\\AppiumFrameworkDesign\\src\\main\\java\\resources\\store.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logInPage = new LogInPage(driver);
    }

    @AfterClass
    public void teardown(){
        driver.quit();
//        service.stop();
    }
}
