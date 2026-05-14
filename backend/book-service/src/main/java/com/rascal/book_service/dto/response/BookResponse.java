package com.rascal.book_service.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record BookResponse(
    Long id,
    String title,
    Integer publicationYear,
    List<String> writerNames,
    List<String> publisherNames,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) { }
