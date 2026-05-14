package com.rascal.book_service.dto.response;

import java.time.LocalDateTime;

public record PublisherResponse(
    Long id,
    String name,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) { }
