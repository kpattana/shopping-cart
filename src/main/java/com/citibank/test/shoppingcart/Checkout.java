package com.citibank.test.shoppingcart;

import java.util.List;

public interface Checkout {
    long calculateTotal(List<String> shoppingCart);
}
