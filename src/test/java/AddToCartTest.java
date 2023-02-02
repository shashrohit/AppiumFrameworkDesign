import TestUtils.BaseTest;
import com.shashank.android.poms.AddToCartPage;
import com.shashank.android.poms.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @BeforeMethod
    public void preSetUp(){
//        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
//        driver.startActivity(activity);
    }

    @Test
    public void addToCart() throws InterruptedException {
        logInPage.selectCountry("Argentina");
        logInPage.setGender("female");
        logInPage.setNameField("Shashank");
        AddToCartPage addToCartPage = logInPage.submitForm();
        Thread.sleep(2000);

        double expectedCost = 0;
        addToCartPage.addItemToCart("Converse All Star");
        expectedCost += addToCartPage.getItemPrice("Converse All Star");

        addToCartPage.addItemToCart("Air Jordan 9 Retro");
        expectedCost += addToCartPage.getItemPrice("Air Jordan 9 Retro");
        Thread.sleep(2000);

        CartPage cartPage = addToCartPage.navigateToCart();
        Thread.sleep(2000);

        double actualCost = cartPage.getDisplayedCost();
        Assert.assertEquals(expectedCost, actualCost);

        cartPage.acceptTerms();
        cartPage.selectSendEmailsOption();
        cartPage.proceedToWebsite();
        Thread.sleep(5000);
    }
}
