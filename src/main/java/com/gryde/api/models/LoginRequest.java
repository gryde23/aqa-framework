package com.gryde.api.models;

public record LoginRequest(
        String email,
        String password
) {
}
