package com.gryde.api.models;

import java.time.LocalDateTime;

public record CreateUserResponse(
        String name,
        String job,
        Integer id,
        LocalDateTime createdAt
) {
}
