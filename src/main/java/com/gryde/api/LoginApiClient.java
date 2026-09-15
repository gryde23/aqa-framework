package com.gryde.api;

import com.gryde.api.models.LoginRequest;
import com.gryde.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class LoginApiClient {

    private final RequestSpecification spec;
    private static final String LOGIN_PATH = "/api/login";

    public LoginApiClient() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.apiBaseUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    public Response login(LoginRequest request) {
        return given()
                .spec(spec)
                .body(request)
                .when()
                .post(LOGIN_PATH);
    }
}
