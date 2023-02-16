import TestUtils.BaseTest;
import com.shashank.android.poms.login.impl.LogInPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//testdata//logindata.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
