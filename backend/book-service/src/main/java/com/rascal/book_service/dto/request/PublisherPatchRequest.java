package com.rascal.book_service.dto.request;

import jakarta.validation.constraints.Size;

public record PublisherPatchRequest(
    @Size(min = 3, max = 50, message = "Nama penulis minimal 3 huruf dan maksimal 50 huruf")
    String name
) {
    public boolean isEmptyPatch() {
        return this.name == null;
    }

    public boolean isNameNull() {
        return this.name() == null;
    }
}
