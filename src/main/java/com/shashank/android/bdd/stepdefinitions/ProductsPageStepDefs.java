package com.shashank.android.bdd.stepdefinitions;

import io.cucumber.java.en.Given;

public class ProductsPageStepDefs extends BaseStepDefs {

    @Given("Add product {string} to cart")
    public void addProductToCart(String product){
        productsPage.addProductToCart(product);
    }

    @Given("Remove product {string} from cart")
    public void removeProductToCart(String product){
        productsPage.removeProductFromCart(product);
    }

    @Given("Click to open cart")
    public void navigateToCart(){
        cartPage = productsPage.navigateToCart();
    }

    @Given("Verify price of product {string} is {double}")
    public void verifyProductPrice(String product, double price){
        productsPage.verifyProductPrice(product, price);
    }
}
