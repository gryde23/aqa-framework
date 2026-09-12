package com.gryde.tests.ui;

import com.codeborne.selenide.Selenide;
import com.gryde.pages.LoginPage;
import com.gryde.tests.base.BaseUITest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class LoginTests extends BaseUITest {

    @Test
    void successLoginOpenInventoryPage() {
        new LoginPage()
                .open()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .successLogin();

        webdriver().shouldHave(urlContaining("/inventory.html"));
    }
}
