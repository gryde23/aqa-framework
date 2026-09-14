package com.gryde.api.models;

public record User(
        Integer id,
        String email,
        String first_name,
        String last_name
) {
}
