package com.shashank.android.bdd.stepdefinitions;

import com.shashank.android.bdd.glue.Hooks;
import com.shashank.android.poms.cart.impl.CartPage;
import com.shashank.android.poms.login.impl.LogInPage;
import com.shashank.android.poms.products.impl.ProductsPage;

public class BaseStepDefs {
    protected LogInPage logInPage = Hooks.getLogInPageObject();
    protected CartPage cartPage;
    protected ProductsPage productsPage;

}
