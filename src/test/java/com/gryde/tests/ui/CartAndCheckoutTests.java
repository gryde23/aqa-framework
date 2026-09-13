package com.gryde.tests.ui;

import com.gryde.pages.CheckoutStepTwoPage;
import com.gryde.pages.InventoryPage;
import com.gryde.pages.LoginPage;
import com.gryde.tests.base.BaseUITest;
import com.gryde.utils.PriceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CartAndCheckoutTests extends BaseUITest {

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
    void fullCheckout() {
        String message = inventoryPage.addItemsToCart(1)
                .goToCart()
                .checkoutStepOne()
                .enterFirstName("Ivan")
                .enterLastName("Ivanov")
                .enterPostalCode("12345")
                .continueCheckout()
                .finish()
                .getCompleteMessage();

        assertThat(message).isEqualTo("Thank you for your order!");
    }

    @Test
    void checkoutWithEmptyFormShowsError() {
        String error = inventoryPage.addItemsToCart(1)
                .goToCart()
                .checkoutStepOne()
                .failCheckout()
                .getError();

        assertThat(error).contains("Error: First Name is required");
    }

    @Test
    void checkOrderSubtotalSum() {
        CheckoutStepTwoPage stepTwoPage = inventoryPage.addItemsToCart(2)
                .goToCart()
                .checkoutStepOne()
                .enterFirstName("Ivan")
                .enterLastName("Ivanov")
                .enterPostalCode("12345")
                .continueCheckout();

        BigDecimal expectedTotal = stepTwoPage.getItemsList().stream()
                .map(item -> PriceUtils.parsePrice(item.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal realTotal = PriceUtils.parsePrice(stepTwoPage.getSubtotal());
        assertThat(realTotal).isEqualByComparingTo(expectedTotal);
    }
}
