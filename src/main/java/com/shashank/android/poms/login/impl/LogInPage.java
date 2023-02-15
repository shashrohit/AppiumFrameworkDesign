package com.shashank.android.poms.login.impl;

import com.shashank.android.poms.products.impl.ProductsPage;
import com.shashank.android.poms.login.ILogInPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LogInPage extends AndroidActions implements ILogInPage {
    @FindBy(id=LogInPageLocators.COUNTRY_DROP_DOWN)
    private WebElement countryDropDown;
    @FindBy(id=LogInPageLocators.NAME_FIELD)
    private WebElement nameField;

    @FindBy(id=LogInPageLocators.MALE_RADIO)
    private WebElement maleRadio;

    @AndroidFindBy(id=LogInPageLocators.FEMALE_RADIO)
    private WebElement femaleRadio;

    @FindBy(id=LogInPageLocators.SUBMIT_BUTTON)
    private WebElement submitButton;

    @FindBy(xpath=LogInPageLocators.ERROR_TOAST_MESSAGE)
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
