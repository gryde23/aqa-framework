package com.gryde.tests.ui;

import com.gryde.pages.LoginPage;
import com.gryde.tests.base.BaseUITest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTestId;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests extends BaseUITest {

    @Test
    void successLoginOpenInventoryPage() {
        new LoginPage()
                .open()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .successLogin();

        $(byTestId("title")).shouldHave(text("Products"));
    }

    @Test
    void loginLockedOutUserShowsError() {
        String error = new LoginPage()
                .open()
                .enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .failedLogin()
                .getError();

        assertThat(error).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/testdata/invalid_login_data.csv",
            numLinesToSkip = 1,
            delimiter = ';',
            emptyValue = "")
    void shouldShowErrorOnInvalidLogin(
            String username,
            String password,
            String expectedError
    ) {
        String error = new LoginPage()
                .open()
                .enterUsername(username)
                .enterPassword(password)
                .failedLogin()
                .getError();

        assertThat(error).isEqualTo(expectedError);
    }


}
