package com.gryde.tests.api;

import com.gryde.api.LoginApiClient;
import com.gryde.api.models.LoginRequest;
import com.gryde.tests.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTests extends BaseApiTest {

    private final LoginApiClient apiClient = new LoginApiClient();

    @Test
    void successLogin_returnsToken() {
        LoginRequest request = new LoginRequest("eve.holt@reqres.in", "pistol");
        Response response = apiClient.login(request);

        response.then().statusCode(200);

        assertThat(response.jsonPath().getString("token")).isNotBlank();
    }

    @Test
    void loginWithoutPassword_returns400() {
        LoginRequest request = new LoginRequest("eve.holt@reqres.in", "");
        Response response = apiClient.login(request);

        response.then().statusCode(400);

        assertThat(response.jsonPath().getString("error")).isEqualTo("Missing password");
    }

    @Test
    void loginNonExistentUser_returns400() {
        LoginRequest request = new LoginRequest("eve.ht@reqres.in", "123");
        Response response = apiClient.login(request);

        response.then().statusCode(400);

        assertThat(response.jsonPath().getString("error")).isEqualTo("user not found");
    }
}
