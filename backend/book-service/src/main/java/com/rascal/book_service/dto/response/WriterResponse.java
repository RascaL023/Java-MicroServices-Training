package com.rascal.book_service.dto.response;

import java.time.LocalDateTime;

public record WriterResponse(
    Long id,
    String name,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {}
