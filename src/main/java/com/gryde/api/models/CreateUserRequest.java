package com.gryde.api.models;

public record CreateUserRequest(
        String name,
        String job
) {
}
