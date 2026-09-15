package com.gryde.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        Integer id,
        String email,
        String first_name,
        String last_name,
        String avatar
) {
}
