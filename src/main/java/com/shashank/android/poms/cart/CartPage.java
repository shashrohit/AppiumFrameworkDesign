package com.shashank.android.poms.cart;

import com.shashank.android.poms.cart.impl.CartPageLocators;
import com.shashank.android.poms.cart.impl.ICartPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    public double getDisplayedCost(){
        return Double.parseDouble(actualCost.getText().substring(1));
    }

    @Override
    public void proceedToWebsite(){
        proceedToWebsite.click();
    }
}
