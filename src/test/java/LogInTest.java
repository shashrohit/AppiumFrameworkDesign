import com.shashank.android.poms.LogInPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LogInTest extends BaseTest {

    @Test(dependsOnMethods = {"logInError"}, dataProvider = "getLoginData")
    public void logIn(HashMap<String, String> input) throws InterruptedException {

        logInPage.selectCountry(input.get("country"));
        logInPage.setGender(input.get("gender"));
        logInPage.setNameField(input.get("name"));
        logInPage.submitForm();
        Thread.sleep(2000);
        logInPage.navigateBack();
    }

    @Test
    public void logInError() throws InterruptedException {
        LogInPage logInForm = new LogInPage(driver);
        logInForm.submitForm();
        logInForm.verifyErrorMessage("Please enter your name");
        Thread.sleep(2000);
    }

    @DataProvider
    public Object[][] getLoginData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "//src//main//java//com//shashank//android//testdata//logindata.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
