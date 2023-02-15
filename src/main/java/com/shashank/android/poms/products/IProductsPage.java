package com.shashank.android.poms.products;

import com.shashank.android.poms.cart.CartPage;

public interface IProductsPage {

    public void addItemToCart(String itemName);

    public CartPage navigateToCart();

    public double getItemPrice(String itemName);
}
