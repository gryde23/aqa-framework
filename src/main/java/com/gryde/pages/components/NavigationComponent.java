package com.gryde.pages.components;

import com.codeborne.selenide.SelenideElement;
import com.gryde.pages.LoginPage;

import static com.codeborne.selenide.Selectors.byTestId;

public class NavigationComponent extends BaseComponent{

    public NavigationComponent(SelenideElement root) {
        super(root);
    }

    public LoginPage logout() {
        root().$(byTestId("logout-sidebar-link")).click();
        return new LoginPage();
    }
}
