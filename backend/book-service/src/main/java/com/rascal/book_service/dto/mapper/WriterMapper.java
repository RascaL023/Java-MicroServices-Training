package com.rascal.book_service.dto.mapper;

import com.rascal.book_service.dto.request.WriterPatchRequest;
import com.rascal.book_service.dto.request.WriterRequest;
import com.rascal.book_service.dto.response.WriterResponse;
import com.rascal.book_service.entity.Writer;

public class WriterMapper {

    public static Writer toWriter(WriterRequest request) {
        Writer writer = new Writer();
        writer.setName(request.name());

        return writer;
    }
    
    public static WriterResponse toResponse(Writer writer) {
        WriterResponse writerResponse = new WriterResponse(
            writer.getId(), 
            writer.getName(), 
            writer.getCreatedAt(), 
            writer.getUpdatedAt(), 
            writer.getDeletedAt()
        );

        return writerResponse;
    }

    public static Writer patch(
        WriterPatchRequest patchRequest, 
        Writer writer
    ) {
        if (!patchRequest.isNameNull()) 
            writer.setName(patchRequest.name());

        return writer;
    }

}
