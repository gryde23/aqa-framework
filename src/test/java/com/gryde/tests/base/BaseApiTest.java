package com.gryde.tests.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;

public class BaseApiTest {

    @BeforeAll
    static void setupApi() {
        RestAssured.filters(new AllureRestAssured());
    }
}
