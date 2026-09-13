package com.gryde.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gryde.pages.CartPage;

import static com.codeborne.selenide.Selectors.byTestId;

public class HeaderComponent extends BaseComponent{

    public HeaderComponent(SelenideElement root) {
        super(root);
    }

    public String getCartBadgeNum() {
        return root().$(byTestId("shopping-cart-badge")).shouldBe(Condition.visible).getText();
    }

    public void clickCartLink() {
        root().$(byTestId("shopping-cart-link")).click();
    }

    public NavigationComponent openNavBar() {
        root().$("#react-burger-menu-btn").click();
        return new NavigationComponent(root().$(".bm-menu"));
    }
}
