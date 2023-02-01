package com.shashank.android.poms;

import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LogInPage extends AndroidActions {
    private final AndroidDriver driver ;

    @FindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropDown;
    @FindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @FindBy(id="com.androidsample.generalstore:id/radioMale")
    private WebElement maleRadio;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleRadio;

    @FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement submitButton;

    @FindBy(xpath="(//android.widget.Toast)[1]")
    private WebElement errorToastMessage;

    public LogInPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setNameField(String name){
        this.nameField.sendKeys(name);
    }

    public void setGender(String gender){
        if(gender.contains("male")){
            this.maleRadio.click();
        }
        else{
            this.femaleRadio.click();
        }
    }

    public void selectCountry(String country){
        this.countryDropDown.click();
        scrollIntoView(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
    }

    public AddToCartPage submitForm(){
        this.submitButton.click();
        return new AddToCartPage(driver);
    }

    public void verifyErrorMessage(String message){
        String errorMessage = this.errorToastMessage.getAttribute("name");
        Assert.assertEquals(errorMessage, message);
    }

}
