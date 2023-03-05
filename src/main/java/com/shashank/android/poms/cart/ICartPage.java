package com.shashank.android.poms.cart;

public interface ICartPage {
    public void selectSendEmailsOption();
    public void acceptTerms() throws InterruptedException;
    public double getTotalPurchaseAmount();
    public void proceedToWebsite();

    public int getProductsCount();

    public String[] getProductsNames();
}
