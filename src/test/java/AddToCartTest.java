import TestUtils.BaseTest;
import com.shashank.android.poms.products.impl.ProductsPage;
import com.shashank.android.poms.cart.CartPage;
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
        ProductsPage products = logInPage.submitForm();
        Thread.sleep(2000);

        double expectedCost = 0;
        products.addItemToCart("Converse All Star");
        expectedCost += products.getItemPrice("Converse All Star");

        products.addItemToCart("Air Jordan 9 Retro");
        expectedCost += products.getItemPrice("Air Jordan 9 Retro");
        Thread.sleep(2000);

        CartPage cartPage = products.navigateToCart();
        Thread.sleep(2000);

        double actualCost = cartPage.getDisplayedCost();
        Assert.assertEquals(expectedCost, actualCost);

        cartPage.acceptTerms();
        cartPage.selectSendEmailsOption();
        cartPage.proceedToWebsite();
        Thread.sleep(5000);
    }
}
