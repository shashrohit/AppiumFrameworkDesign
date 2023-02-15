package com.shashank.android.poms.cart;

import com.shashank.android.poms.cart.impl.CartPageLocators;
import com.shashank.android.poms.cart.impl.ICartPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class CartPage extends AndroidActions implements ICartPage {

    private final AndroidDriver driver;

    @FindBy(id= CartPageLocators.ACTUAL_COST)
    private WebElement actualCost;

    @FindBy(id=CartPageLocators.TERMS_BUTTON)
    private WebElement termsBtn;

    @FindBy(id=CartPageLocators.TERMS_CLOSE_BUTTON)
    private WebElement termsCloseBtn;
    @FindBy(xpath=CartPageLocators.SEND_EMAIL_CHECKBOX)
    private WebElement sendEmailsCheckbox;

    @FindBy(id=CartPageLocators.PROCEED_TO_WEBSITE)
    private WebElement proceedToWebsite;

    @FindBy(id=CartPageLocators.PRODUCT_NAME)
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
