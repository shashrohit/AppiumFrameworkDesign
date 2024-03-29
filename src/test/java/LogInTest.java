import TestUtils.BaseTest;
import com.shashank.android.utils.AppiumUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import static TestUtils.Constants.LOGIN_DATA_FILE;

public class LogInTest extends BaseTest {

    @Test(dependsOnMethods = {"logInError"}, dataProvider = "getLoginData")
    public void logIn(HashMap<String, String> input) throws InterruptedException {
        log.info("Log in positive test has started");
        logInPage.selectCountry(input.get("country"));
        logInPage.setGender(input.get("gender"));
        logInPage.setNameField(input.get("name"));
        logInPage.submitForm();
        Thread.sleep(2000);
        logInPage.navigateBack();
        log.info("Log in positive test has finished");
    }

    @Test
    public void logInError() throws InterruptedException {
        log.info("Log in negative test has started");
        logInPage.submitForm();
        logInPage.verifyErrorMessage("Please enter your name");
        Thread.sleep(2000);
        log.info("Log in negative test has finished");
    }

    @DataProvider
    public Object[][] getLoginData() throws IOException {
        List<HashMap<String, String>> data = AppiumUtils.getJsonData(LOGIN_DATA_FILE);
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
