package com.shashank.android.poms.products.impl;

import com.shashank.android.poms.cart.impl.CartPage;
import com.shashank.android.poms.products.IProductsPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends AndroidActions implements IProductsPage {

    @FindBy(how=How.ID, using=ProductsPageLocators.ADD_TO_CART_BUTTONS)
    private List<WebElement> addToCartButtons;

    @FindBy(how= How.ID, using=ProductsPageLocators.PRODUCTS)
    private List<WebElement> products;

    @FindBy(how=How.ID, using=ProductsPageLocators.PRODUCT_COSTS)
    private List<WebElement> productCosts;

    @FindBy(how=How.ID, using=ProductsPageLocators.NAV_TO_CART_BTN)
    @CacheLookup
    private WebElement navToCartBtn;

    public ProductsPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void addProductToCart(String itemName){
        scrollIntoView(itemName);
        products.stream()
                .filter(p -> p.getText().equalsIgnoreCase(itemName))
                .collect(Collectors.toList())
                .get(0)
                .click();
    }

    @Override
    public void removeProductFromCart(String itemName) {
        this.addProductToCart(itemName);
    }

    @Override
    public CartPage navigateToCart(){
        this.navToCartBtn.click();
        return new CartPage(driver);
    }

    @Override
    public double getProductPrice(String itemName){
        scrollIntoView(itemName);
        for(int i=0; i < products.size(); i++){
            if(products.get(i).getText().equalsIgnoreCase(itemName)){
               return Double.parseDouble(productCosts.get(i).getText().substring(1));
            }
        }
        return 0;
    }

    @Override
    public void verifyProductPrice(String itemName, double price){
        Assert.assertEquals(getProductPrice(itemName), price);
    }
}
