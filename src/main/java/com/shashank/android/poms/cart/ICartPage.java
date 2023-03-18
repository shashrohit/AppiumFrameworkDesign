package com.shashank.android.poms.cart;

 public interface ICartPage {
     void selectSendEmailsOption();
     void acceptTerms() throws InterruptedException;
     double getTotalPurchaseAmount();

     void verifyTotalPurchaseAmount(double amount);
     void proceedToWebsite();

     int getProductsCount();
     void verifyProductsCount(int count);

     String[] getProductsNames();
}
