package com.gryde.api.models;

import java.util.List;

public record UsersListResponse(
        Integer page,
        Integer per_page,
        Integer total,
        Integer total_pages,
        List<User> data
) {
}
