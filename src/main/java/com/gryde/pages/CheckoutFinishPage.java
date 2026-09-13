package com.gryde.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTestId;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutFinishPage extends BasePage<CheckoutFinishPage>{

    private final SelenideElement completeMessage = $(byTestId("complete-header"));

    @Override
    protected String path() {
        return "/checkout-complete.html";
    }

    @Override
    protected SelenideElement uniqueElement() {
        return null;
    }

    public String getCompleteMessage() {
        return completeMessage.shouldBe(Condition.visible).getText();
    }
}
