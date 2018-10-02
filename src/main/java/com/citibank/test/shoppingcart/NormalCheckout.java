package com.citibank.test.shoppingcart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NormalCheckout implements Checkout {

    private final Map<String, Long> retailPrice = new HashMap<String, Long>();

    public void addPrice(String item, long price) {
        retailPrice.put(item, price);
    }

    public long calculateTotal(List<String> shoppingCart) {
        return shoppingCart.stream().filter(item -> retailPrice.containsKey(item)).mapToLong(retailPrice::get).sum();
    }
}
