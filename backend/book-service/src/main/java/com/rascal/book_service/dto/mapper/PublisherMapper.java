package com.rascal.book_service.dto.mapper;

import com.rascal.book_service.dto.request.PublisherPatchRequest;
import com.rascal.book_service.dto.request.PublisherRequest;
import com.rascal.book_service.dto.response.PublisherResponse;
import com.rascal.book_service.entity.Publisher;

public class PublisherMapper {

    public static Publisher toPublisher(PublisherRequest request) {
        Publisher publihser = new Publisher();
        publihser.setName(request.name());

        return publihser;
    }
    
    public static PublisherResponse toResponse(Publisher publihser) {
        PublisherResponse publisherResponse = new PublisherResponse(
            publihser.getId(), 
            publihser.getName(), 
            publihser.getCreatedAt(), 
            publihser.getUpdatedAt(), 
            publihser.getDeletedAt()
        );

        return publisherResponse;
    }

    public static Publisher patch(
        PublisherPatchRequest patchRequest, 
        Publisher publisher
    ) {
        if (!patchRequest.isNameNull()) 
            publisher.setName(patchRequest.name());

        return publisher;
    }

}

