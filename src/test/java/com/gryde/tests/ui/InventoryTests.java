package com.gryde.tests.ui;

import com.gryde.pages.InventoryPage;
import com.gryde.pages.LoginPage;
import com.gryde.tests.base.BaseUITest;
import com.gryde.utils.PriceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryTests extends BaseUITest {

    private InventoryPage inventoryPage;

    @BeforeEach
    void login() {
        inventoryPage = new LoginPage()
                .open()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .successLogin();
    }

    @Test
    void addItemToCart() {
        inventoryPage.addItemsToCart(1);

        assertThat(inventoryPage.getAmountOfItemsInCart()).isEqualTo("1");
    }

    @Test
    void addItemsToCartAndRemoveOne() {
        String amountOfItems = inventoryPage.addItemsToCart(2)
                .addItemToCart("Sauce Labs Onesie")
                .removeItemFromCart("Sauce Labs Onesie")
                .getAmountOfItemsInCart();

        assertThat(amountOfItems).isEqualTo("2");
    }

    @Test
    void selectSortForPriceAndCheck() {
        List<BigDecimal> prices = inventoryPage.selectItemsSort("lohi")
                .getAllPrices().stream()
                .map(PriceUtils::parsePrice)
                .toList();

        assertThat(prices).isSorted();
    }
}
