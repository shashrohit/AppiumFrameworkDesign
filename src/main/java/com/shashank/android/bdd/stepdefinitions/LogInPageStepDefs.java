package com.shashank.android.bdd.stepdefinitions;

import io.cucumber.java.en.Given;

public class LogInPageStepDefs extends BaseStepDefs {

    @Given("Enter {string} in the name field")
    public void setNameField(String name){
        logInPage.setNameField(name);
    }
    @Given("Select {string} as gender")
    public void selectGender(String gender){
        logInPage.setGender(gender);
    }
    @Given("Select {string} as country")
    public void selectCountry(String country){
        logInPage.selectCountry(country);
    }
    @Given("Click on submit button")
    public void submitForm(){
        productsPage = logInPage.submitForm();
    }

    @Given("Verify error message is - {string}")
    public void verifyErrorMessage(String errorMessage){
        logInPage.verifyErrorMessage(errorMessage);
    }

}
