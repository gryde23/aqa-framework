package com.gryde.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateUserResponse(
        String name,
        String job,
        Integer id,
        String createdAt
) {
}
