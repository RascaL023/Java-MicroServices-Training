package com.rascal.book_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rascal.book_service.dto.request.PublisherPatchRequest;
import com.rascal.book_service.dto.request.PublisherRequest;
import com.rascal.book_service.entity.Publisher;

public interface PublisherService {

    List<Publisher> getPublisherByIds(List<Long> publisherIds);
    Page<Publisher> getAllPaged(Pageable pageable);
    Publisher getById(Long id);
    Publisher insert(PublisherRequest request);
    Publisher patchUpdate(Long id, PublisherPatchRequest request);
    void deleteById(Long id);
    
}
