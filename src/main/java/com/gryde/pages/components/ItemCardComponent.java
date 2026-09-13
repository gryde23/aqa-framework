package com.gryde.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.math.BigDecimal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTestId;

public class ItemCardComponent extends BaseComponent{

    public ItemCardComponent(SelenideElement root) {
        super(root);
    }

    public String getTitle() {
        return root().$(byTestId("inventory-item-name")).shouldBe(Condition.visible).getText();
    }

    public String getPrice() {
        return root().$(byTestId("inventory-item-price")).getText();
    }

    public void addToCart() {
        root().$(".btn_primary.btn_inventory").click();
    }

    public void removeFromCart() {
        root().$(".btn_secondary.btn_inventory").click();
    }
}
