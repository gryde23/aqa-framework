package com.gryde.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.gryde.pages.components.CartItemComponent;
import com.gryde.pages.components.ItemCardComponent;

import java.util.List;

import static com.codeborne.selenide.Selectors.byTestId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutStepTwoPage extends BasePage<CheckoutStepTwoPage>{

    private final SelenideElement subtotal = $(byTestId("subtotal-label"));
    private final SelenideElement finishButton = $(byTestId("finish"));
    private final ElementsCollection items = $$(".cart_item");

    @Override
    protected String path() {
        return "/checkout-step-two.html";
    }

    @Override
    protected SelenideElement uniqueElement() {
        return null;
    }

    public String getSubtotal() {
        return subtotal.shouldBe(Condition.visible).getText().split("\\$")[1].trim();
    }

    public CheckoutFinishPage finish() {
        finishButton.click();
        return new CheckoutFinishPage();
    }

    public List<CartItemComponent> getItemsList() {
        return items.asFixedIterable().stream()
                .map(CartItemComponent::new)
                .toList();
    }
}
