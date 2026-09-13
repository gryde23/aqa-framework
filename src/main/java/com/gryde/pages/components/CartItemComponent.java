package com.gryde.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTestId;

public class CartItemComponent extends BaseComponent{

    public CartItemComponent(SelenideElement root) {
        super(root);
    }

    public String getItemName() {
        return root().$(byTestId("inventory-item-name")).shouldBe(Condition.visible).getText();
    }

    public String getPrice() {
        return root().$(byTestId("inventory-item-price")).shouldBe(Condition.visible).getText();
    }

    public void removeItem() {
        root().$(".cart_button").click();
    }
}
