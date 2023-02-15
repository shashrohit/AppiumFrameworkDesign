package com.shashank.android.poms.login;

import com.shashank.android.poms.products.impl.ProductsPage;

public interface ILogInPage {

    public void setNameField(String name);
    public void setGender(String gender);
    public void selectCountry(String country);
    public ProductsPage submitForm();
    public void verifyErrorMessage(String message);
}
