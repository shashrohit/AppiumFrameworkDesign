package com.shashank.android.poms.cart.impl;

import com.shashank.android.poms.cart.ICartPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.*;

public class CartPage extends AndroidActions implements ICartPage {

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
    public void verifyTotalPurchaseAmount(double amount){
        Assert.assertEquals(getTotalPurchaseAmount(), amount);
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
    public void verifyProductsCount(int count) {
        Assert.assertEquals(getProductsCount(), count);
    }

    @Override
    public String[] getProductsNames() {
        return products.stream()
                .map(WebElement::getText)
                .toArray(String[]::new);
    }
}
