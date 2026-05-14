package com.rascal.book_service.dto.request;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
    @NotBlank(message = "Judul buku tidak valid")
    String title,
    
    @NotNull(message = "Tahun rilis tidak boleh kosong")
    @Min(value = 0, message = "Tahun rilis tidak valid")
    Integer publishedYear,

    @NotEmpty(message = "Penulis buku tidak boleh kosong")
    List<Long> writerIds,

    @NotEmpty(message = "Publisher buku tidak boleh kosong")
    List<Long> publisherIds
) { }
