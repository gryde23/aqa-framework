package com.gryde.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsersListResponse(
        Integer page,
        Integer per_page,
        Integer total,
        Integer total_pages,
        List<User> data
) {
}
