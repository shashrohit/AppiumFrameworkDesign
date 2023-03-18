package com.shashank.android.poms.products;

import com.shashank.android.poms.cart.impl.CartPage;

public interface IProductsPage {

    void addProductToCart(String itemName);

    void removeProductFromCart(String itemName);

    CartPage navigateToCart();

    double getProductPrice(String itemName);

    void verifyProductPrice(String itemName, double price);
}
