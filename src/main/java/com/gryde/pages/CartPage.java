package com.gryde.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTestId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage extends BasePage<CartPage>{

    private final SelenideElement checkoutButton = $(byTestId("checkout"));
    private final ElementsCollection items = $$(".inventory-item");

    @Override
    protected String path() {
        return "/cart.html";
    }

    @Override
    protected SelenideElement uniqueElement() {
        return null;
    }

    public CheckoutStepOnePage checkoutStepOne() {
        checkoutButton.click();
        return new CheckoutStepOnePage();
    }

    public int getAmountOfItems() {
        return items.size();
    }

}
