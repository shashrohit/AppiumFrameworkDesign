import com.shashank.android.poms.AddToCartPage;
import com.shashank.android.poms.CartPage;
import com.shashank.android.poms.LogInPage;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AddToCartTest extends BaseTest {

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
