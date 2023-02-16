package com.shashank.android.poms.cart;

import com.shashank.android.poms.cart.impl.CartPageLocators;
import com.shashank.android.poms.cart.impl.ICartPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class CartPage extends AndroidActions implements ICartPage {

    private final AndroidDriver driver;

    @FindBy(how=How.ID, using=CartPageLocators.ACTUAL_COST)
    @CacheLookup
    private WebElement actualCost;

    @FindBy(how=How.ID, using=CartPageLocators.TERMS_BUTTON)
    @CacheLookup
    private WebElement termsBtn;

    @FindBy(how=How.ID, using=CartPageLocators.TERMS_CLOSE_BUTTON)
    @CacheLookup
    private WebElement termsCloseBtn;
    @FindBy(how=How.XPATH, using=CartPageLocators.SEND_EMAIL_CHECKBOX)
    @CacheLookup
    private WebElement sendEmailsCheckbox;

    @FindBy(how=How.ID, using=CartPageLocators.PROCEED_TO_WEBSITE)
    @CacheLookup
    private WebElement proceedToWebsite;

    @FindBy(how=How.ID, using=CartPageLocators.PRODUCT_NAME)
    @CacheLookup
    private List<WebElement> products;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void selectSendEmailsOption(){
        sendEmailsCheckbox.click();
    }

    @Override
    public void acceptTerms() throws InterruptedException {
        longPress(termsBtn);
        Thread.sleep(3000);
        termsCloseBtn.click();
    }

    @Override
    public double getTotalPurchaseAmount(){
        return Double.parseDouble(actualCost.getText().substring(1));
    }

    @Override
    public void proceedToWebsite(){
        proceedToWebsite.click();
    }

    @Override
    public int getProductsCount() {
        return products.size();
    }

    @Override
    public String[] getProductsNames() {
        List<String> productNames = new ArrayList<>();
        for(WebElement product: products){
            productNames.add(product.getText());
        }
        return productNames.toArray(new String[0]);
    }
}
