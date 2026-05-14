package com.rascal.book_service.dto.request;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record BookPatchRequest(
    @Size(min = 3, max = 50, message = "Nama penulis minimal 3 huruf dan maksimal 50 huruf")
    String title,
    
    @Min(value = 0, message = "Tahun rilis tidak valid")
    Integer publishedYear,

    List<Long> writerIds,
    List<Long> publisherIds
) {
    public boolean isEmptyPatch() {
        return title == null && publishedYear == null &&
        writerIds == null && publisherIds == null;
    }

    public boolean isEmptyTitle() {
        return this.title() == null;
    }

    public boolean isEmptyPublihsedYear() {
        return this.publishedYear() == null;
    }

    public boolean isEmptyWriterIds() {
        return this.writerIds() == null || 
        this.writerIds().isEmpty();
    }

    public boolean isEmptyPublisherIds() {
        return this.publisherIds() == null || 
        this.publisherIds().isEmpty();
    }

}
