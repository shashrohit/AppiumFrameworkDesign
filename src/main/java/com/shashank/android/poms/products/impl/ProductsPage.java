package com.shashank.android.poms.products.impl;

import com.shashank.android.poms.cart.CartPage;
import com.shashank.android.poms.products.IProductsPage;
import com.shashank.android.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductsPage extends AndroidActions implements IProductsPage {

    @FindBy(id=ProductsPageLocators.ADD_TO_CART_BUTTONS)
    private List<WebElement> addToCartButtons;

    @FindBy(id=ProductsPageLocators.PRODUCTS)
    private List<WebElement> products;

    @FindBy(id=ProductsPageLocators.PRODUCT_COSTS)
    private List<WebElement> productCosts;

    @FindBy(id=ProductsPageLocators.NAV_TO_CART_BTN)
    private WebElement navToCartBtn;

    public ProductsPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void addProductToCart(String itemName){
        scrollIntoView(itemName);
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getText().equalsIgnoreCase(itemName)){
                addToCartButtons.get(i).click();
            }
        }
    }

    @Override
    public void removeProductToCart(String itemName) {
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

}
