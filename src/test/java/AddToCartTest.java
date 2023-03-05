import TestUtils.BaseTest;
import com.shashank.android.poms.products.impl.ProductsPage;
import com.shashank.android.poms.cart.impl.CartPage;
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
        log.info("Add to cart test has started");

        log.info("Logging in");
        logInPage.selectCountry("Argentina");
        logInPage.setGender("female");
        logInPage.setNameField("Shashank");
        ProductsPage products = logInPage.submitForm();
        Thread.sleep(2000);
        log.info("Log in successful");

        log.info("Adding products to cart");
        String [] testProducts = new String[]{"Converse All Star", "Air Jordan 9 Retro"};
        double expectedCost = 0;
        products.addProductToCart(testProducts[0]);
        expectedCost += products.getProductPrice(testProducts[0]);

        products.addProductToCart(testProducts[1]);
        expectedCost += products.getProductPrice(testProducts[1]);
        Thread.sleep(2000);

        log.info("Navigating to cart");
        CartPage cartPage = products.navigateToCart();
        Thread.sleep(2000);

        log.info("Verifying cart");
        double actualCost = cartPage.getTotalPurchaseAmount();
        Assert.assertEquals(expectedCost, actualCost);

        Assert.assertEquals(cartPage.getProductsCount(), 2);
        String[] actualProducts = cartPage.getProductsNames();

        Assert.assertEquals(testProducts, actualProducts);

        log.info("Navigating to website");
        cartPage.acceptTerms();
        cartPage.selectSendEmailsOption();
        cartPage.proceedToWebsite();
        Thread.sleep(5000);
    }
}
