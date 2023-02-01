package com.shashank.android.poms;

import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AndroidActions {

    private final AndroidDriver driver;

    @FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement actualCost;

    @FindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsBtn;

    @FindBy(id = "android:id/button1")
    private WebElement termsCloseBtn;
    @FindBy(xpath = "//android.widget.CheckBox")
    private WebElement sendEmailsCheckbox;

    @FindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedToWebsite;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectSendEmailsOption(){
        sendEmailsCheckbox.click();
    }

    public void acceptTerms() throws InterruptedException {
        longPress(termsBtn);
        Thread.sleep(3000);
        termsCloseBtn.click();
    }

    public double getDisplayedCost(){
        return Double.parseDouble(actualCost.getText().substring(1));
    }

    public void proceedToWebsite(){
        proceedToWebsite.click();
    }
}
