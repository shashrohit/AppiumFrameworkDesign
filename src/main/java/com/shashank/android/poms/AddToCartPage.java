package com.shashank.android.poms;

import com.shashank.android.utils.MobileActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddToCartPage extends MobileActions {

    private final AndroidDriver driver;
    @FindBy(id = "com.androidsample.generalstore:id/productAddCart")
    private List<WebElement> addTOCartButtons;

    @FindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> products;

    @FindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productCosts;

    @FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement navToCartBtn;

    public AddToCartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToCart(String itemName){
        scrollIntoView(itemName);
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getText().equalsIgnoreCase(itemName)){
                addTOCartButtons.get(i).click();
            }
        }
    }

    public CartPage navigateToCart(){
        this.navToCartBtn.click();
        return new CartPage(driver);
    }

    public double getItemPrice(String itemName){
        scrollIntoView(itemName);
        for(int i=0; i < products.size(); i++){
            if(products.get(i).getText().equalsIgnoreCase(itemName)){
               return Double.parseDouble(productCosts.get(i).getText().substring(1));
            }
        }
        return 0;
    }

}
