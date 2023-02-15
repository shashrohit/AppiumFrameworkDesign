package com.shashank.android.poms.cart.impl;

import com.shashank.android.poms.products.impl.ProductsPage;

public interface ICartPage {
    public void selectSendEmailsOption();
    public void acceptTerms() throws InterruptedException;
    public double getDisplayedCost();
    public void proceedToWebsite();
}
