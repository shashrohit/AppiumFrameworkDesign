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


        String [] testProducts = new String[]{"Converse All Star", "Air Jordan 9 Retro"};
        double expectedCost = 0;
        products.addProductToCart(testProducts[0]);
        expectedCost += products.getProductPrice(testProducts[0]);

        products.addProductToCart(testProducts[1]);
        expectedCost += products.getProductPrice(testProducts[1]);
        Thread.sleep(2000);

        CartPage cartPage = products.navigateToCart();
        Thread.sleep(2000);

        double actualCost = cartPage.getTotalPurchaseAmount();
        Assert.assertEquals(expectedCost, actualCost);

        Assert.assertEquals(cartPage.getProductsCount(), 2);
        String[] actualProducts = cartPage.getProductsNames();

        Assert.assertEquals(testProducts, actualProducts);

        cartPage.acceptTerms();
        cartPage.selectSendEmailsOption();
        cartPage.proceedToWebsite();
        Thread.sleep(5000);
    }
}
