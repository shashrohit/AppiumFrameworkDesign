package com.shashank.android.bdd.stepdefinitions;

import io.cucumber.java.en.Given;

public class CartPageStepDefs extends BaseStepDefs {

    @Given("Select send email option")
    public void selectSendEmailsOption(){
        cartPage.selectSendEmailsOption();
    }

    @Given("Accept terms")
    public void acceptTerms() throws InterruptedException {
        cartPage.acceptTerms();
    }

    @Given("Proceed to website")
    public void proceedToWebsite(){
        cartPage.proceedToWebsite();
    }

    @Given("Verify total purchase amount is {double}")
    public void verifyTotalPurchaseAmount(double amount){
        cartPage.verifyTotalPurchaseAmount(amount);
    }
    @Given("Verify products count is {int}")
    public void verifyProductsCount(int count){
        cartPage.verifyProductsCount(count);
    }

}
