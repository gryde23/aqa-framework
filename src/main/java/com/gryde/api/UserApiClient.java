package com.gryde.api;

import com.gryde.api.models.CreateUserRequest;
import com.gryde.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private final RequestSpecification spec;
    private static final String USERS_PATH = "/api/users";
    private static final String USER_BY_ID_PATH = "/api/users/{id}";

    public UserApiClient() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.apiBaseUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    public Response getUser(int id) {
        return given()
                .spec(spec)
                .pathParam("id", id)
                .when()
                .get(USER_BY_ID_PATH);
    }

    public Response getUsers(int page) {
        return given()
                .spec(spec)
                .queryParam("page", page)
                .when()
                .get(USERS_PATH);
    }

    public Response createUser(CreateUserRequest request) {
        return given()
                .spec(spec)
                .body(request)
                .when()
                .post(USERS_PATH);
    }
}
