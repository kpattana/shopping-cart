package com.citibank.test.shoppingcart;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTest {

    static Checkout productPriceDiscount = new NormalCheckout();

    @BeforeClass
    public static void setupProductsAndDiscounts() {
        ((NormalCheckout) productPriceDiscount).addPrice("Apples", 35);
        ((NormalCheckout) productPriceDiscount).addPrice("Bananas",20);
        ((NormalCheckout) productPriceDiscount).addPrice("Melons",50);
        ((NormalCheckout) productPriceDiscount).addPrice("Lime", 15);
        productPriceDiscount = new DiscountDecorator(productPriceDiscount, "Melons", 2, 50);
        productPriceDiscount = new DiscountDecorator(productPriceDiscount, "Lime", 3, 15);

    }

    @Test
    public void testEmptyShoppingCart() {
        List<String> shoppingCart = new ArrayList<>();
        Assert.assertEquals(productPriceDiscount.calculateTotal(shoppingCart), 0);
    }

    @Test
    public void testNoDiscountShoppingCart() {
        List<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Apples");
        shoppingCart.add("Bananas");
        shoppingCart.add("Melons");
        shoppingCart.add("Lime");

        Assert.assertEquals(productPriceDiscount.calculateTotal(shoppingCart), 120);
    }

    @Test
    public void testDiscountShoppingCart() {
        List<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Apples");
        shoppingCart.add("Bananas");
        shoppingCart.add("Melons");
        shoppingCart.add("Lime");
        shoppingCart.add("Melons");
        shoppingCart.add("Lime");
        shoppingCart.add("Lime");

        Assert.assertEquals(productPriceDiscount.calculateTotal(shoppingCart), 135);
    }

    @AfterClass
    public static void teardownProductsAndDiscounts() {
        productPriceDiscount = null;
    }
}
