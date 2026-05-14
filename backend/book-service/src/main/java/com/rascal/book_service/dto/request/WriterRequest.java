package com.rascal.book_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WriterRequest(
    @NotBlank(message = "Nama penulis tidak valid")
    @Size(min = 3, max = 50, message = "Nama penulis minimal 3 huruf dan maksimal 50 huruf")
    String name
) { }
