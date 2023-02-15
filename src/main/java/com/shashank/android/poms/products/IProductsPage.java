package com.shashank.android.poms.products;

import com.shashank.android.poms.cart.CartPage;

public interface IProductsPage {

    public void addProductToCart(String itemName);

    public void removeProductToCart(String itemName);

    public CartPage navigateToCart();

    public double getProductPrice(String itemName);
}
