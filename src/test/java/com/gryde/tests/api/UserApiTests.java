package com.gryde.tests.api;

import com.gryde.api.UserApiClient;
import com.gryde.api.models.*;
import com.gryde.tests.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class UserApiTests extends BaseApiTest {

    private final UserApiClient apiClient = new UserApiClient();

    @Test
    void getUsersList_returns200() {
        Response response = apiClient.getUsers(1);
        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users-list-schema.json"));
    }

    @Test
    void getUsersPage2_returns200() {
        Response response = apiClient.getUsers(2);
        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users-list-schema.json"));

        UsersListResponse users = response.as(UsersListResponse.class);

        assertThat(users).satisfies(u -> {
            assertThat(u.page()).isEqualTo(2);
            assertThat(u.total_pages()).isEqualTo(2);
            assertThat(u.total()).isEqualTo(12);
            assertThat(u.data().size()).isEqualTo(6);
        });
    }

    @Test
    void getUserById_returns200AndUser() {
        Response response = apiClient.getUser(2);
        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/single-user-schema.json"));

        User user = response.jsonPath().getObject("data", User.class);

        assertThat(user.id()).isEqualTo(2);
    }

    @Test
    void getFakeUser_returns404() {
        Response response = apiClient.getUser(999);
        response.then().statusCode(404);

        assertThat(response.getBody().asString()).isEqualTo("{}");
    }

    @Test
    void createUser_returns201() {
        CreateUserRequest request = new CreateUserRequest("Ivan", "baker");
        Response response = apiClient.createUser(request);
        response.then().statusCode(201);

        CreateUserResponse user = response.as(CreateUserResponse.class);

        assertThat(user).satisfies(u -> {
            assertThat(u.id()).isNotNull();
            assertThat(u.createdAt()).isNotNull();
            assertThat(u.name()).isEqualTo("Ivan");
            assertThat(u.job()).isEqualTo("baker");
        });
    }

    @Test
    void updateUserCompletely_returns200() {
        UpdateUserRequest user = new UpdateUserRequest("baker");

        Response response = apiClient.updateUser(2, user);
        response.then().statusCode(200);

        assertThat(response.jsonPath().getString("updatedAt")).isNotBlank();
        assertThat(response.jsonPath().getString("job")).isEqualTo("baker");
    }

    @Test
    void updateUserJob_returns200() {
        Response response = apiClient.updateJob(2, "baker");
        response.then().statusCode(200);

        assertThat(response).satisfies(r -> {
            assertThat(r.jsonPath().getString("job")).isEqualTo("baker");
            assertThat(r.jsonPath().getString("updatedAt")).isNotBlank();
        });
    }

    @Test
    void deleteUser_returns204() {
        Response response = apiClient.deleteUser(2);
        response.then().statusCode(204);
        assertThat(response.getBody().asString()).isEmpty();
    }
}
