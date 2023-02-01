import com.shashank.android.poms.LogInPage;
import org.testng.annotations.Test;

public class LogInTest extends BaseTest {

    @Test(dependsOnMethods = {"logInError"})
    public void logIn() throws InterruptedException {
        LogInPage logInForm = new LogInPage(driver);
        logInForm.selectCountry("Argentina");
        logInForm.setGender("male");
        logInForm.setNameField("Shashank");
        logInForm.submitForm();
        Thread.sleep(2000);
    }

    @Test
    public void logInError() throws InterruptedException {
        LogInPage logInForm = new LogInPage(driver);
        logInForm.submitForm();
        logInForm.verifyErrorMessage("Please enter your name");
        Thread.sleep(2000);
    }
}
