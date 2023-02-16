package com.shashank.android.poms.login.impl;

import com.shashank.android.poms.products.impl.ProductsPage;
import com.shashank.android.poms.login.ILogInPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LogInPage extends AndroidActions implements ILogInPage {
    @FindBy(how=How.ID, using=LogInPageLocators.COUNTRY_DROP_DOWN)
    @CacheLookup
    private WebElement countryDropDown;
    @FindBy(how=How.ID, using=LogInPageLocators.NAME_FIELD)
    @CacheLookup
    private WebElement nameField;

    @FindBy(how=How.ID, using=LogInPageLocators.MALE_RADIO)
    @CacheLookup
    private WebElement maleRadio;

    @FindBy(how=How.ID, using=LogInPageLocators.FEMALE_RADIO)
    @CacheLookup
    private WebElement femaleRadio;

    @FindBy(how=How.ID, using=LogInPageLocators.SUBMIT_BUTTON)
    @CacheLookup
    private WebElement submitButton;

    @FindBy(how=How.XPATH, using=LogInPageLocators.ERROR_TOAST_MESSAGE)
    @CacheLookup
    private WebElement errorToastMessage;

    public LogInPage(AndroidDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void setNameField(String name){
        this.nameField.sendKeys(name);
    }

    @Override
    public void setGender(String gender){
        if(gender.contains("male")){
            this.maleRadio.click();
        }
        else{
            this.femaleRadio.click();
        }
    }

    @Override
    public void selectCountry(String country){
        this.countryDropDown.click();
        scrollIntoView(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
    }

    @Override
    public ProductsPage submitForm(){
        this.submitButton.click();
        return new ProductsPage(driver);
    }

    @Override
    public void verifyErrorMessage(String message){
        String errorMessage = this.errorToastMessage.getAttribute("name");
        Assert.assertEquals(errorMessage, message);
    }
}
