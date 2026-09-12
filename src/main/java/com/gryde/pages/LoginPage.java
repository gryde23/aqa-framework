package com.gryde.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTestId;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage<LoginPage>{

    private final SelenideElement usernameInput = $(byTestId("username"));
    private final SelenideElement passwordInput = $(byTestId("password"));
    private final SelenideElement loginButton = $(byTestId("login-button"));
    private final SelenideElement error = $(byTestId("error"));

    @Override
    protected String path() {
        return "";
    }

    @Override
    protected SelenideElement uniqueElement() {
        return $(".login_logo").shouldBe(Condition.visible);
    }

    public LoginPage open() {
        Selenide.open(path());
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage failedLogin() {
        loginButton.click();
        return this;
    }

    public InventoryPage successLogin() {
        loginButton.click();
        return new InventoryPage();
    }

    public String getError() {
        return error.shouldBe(Condition.visible).getText();
    }
}
