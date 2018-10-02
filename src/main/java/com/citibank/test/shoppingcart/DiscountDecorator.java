package com.citibank.test.shoppingcart;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DiscountDecorator implements Checkout {

    private final Checkout priorPriceCheckout;
    private final String discountedItem;
    private final int minAmount;
    private final long discountValue;

    public DiscountDecorator(Checkout priorPriceCheckout, String discountedItem, int minAmount, long discountValue) {
        this.priorPriceCheckout = priorPriceCheckout;
        this.discountedItem = discountedItem;
        this.minAmount = (minAmount==0)?1:minAmount;
        this.discountValue = discountValue;
    }


    public long calculateTotal(List<String> shoppingCart) {
        int count = Collections.frequency(shoppingCart, discountedItem);
        long deduction = (count/minAmount) * discountValue;
        return priorPriceCheckout.calculateTotal(shoppingCart) - deduction;
    }
}
