package com.gryde.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTestId;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutStepOnePage extends BasePage<CheckoutStepOnePage>{

    private final SelenideElement firstName = $(byTestId("firstName"));
    private final SelenideElement lastName = $(byTestId("lastName"));
    private final SelenideElement postalCode = $(byTestId("postalCode"));
    private final SelenideElement continueButton = $(byTestId("continue"));
    private final SelenideElement error = $(byTestId("error"));

    @Override
    protected String path() {
        return "/checkout-step-one.html";
    }

    @Override
    protected SelenideElement uniqueElement() {
        return null;
    }

    public CheckoutStepOnePage enterFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public CheckoutStepOnePage enterLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public CheckoutStepOnePage enterPostalCode(String value) {
        postalCode.setValue(value);
        return this;
    }

    public CheckoutStepTwoPage continueCheckout() {
        continueButton.click();
        return new CheckoutStepTwoPage();
    }

    public CheckoutStepOnePage failCheckout() {
        continueButton.click();
        return this;
    }

    public String getError() {
        return error.shouldBe(Condition.visible).getText();
    }

}
