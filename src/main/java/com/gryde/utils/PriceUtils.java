package com.gryde.utils;

import java.math.BigDecimal;

public class PriceUtils {

    private PriceUtils() {}

    public static BigDecimal parsePrice(String rawPrice) {
        return new BigDecimal(rawPrice.replace("$", "").trim());
    }
}
