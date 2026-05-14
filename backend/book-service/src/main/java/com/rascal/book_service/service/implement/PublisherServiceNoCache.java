package com.rascal.book_service.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rascal.book_service.dto.mapper.PublisherMapper;
import com.rascal.book_service.dto.request.PublisherPatchRequest;
import com.rascal.book_service.dto.request.PublisherRequest;
import com.rascal.book_service.entity.Publisher;
import com.rascal.book_service.exception.BadRequestException;
import com.rascal.book_service.exception.NotFoundException;
import com.rascal.book_service.repository.PublisherRepository;
import com.rascal.book_service.service.PublisherService;

@Service
public class PublisherServiceNoCache implements PublisherService {

    @Autowired private PublisherRepository publisherRepository;

    public List<Publisher> getPublisherByIds(List<Long> publisherIds) {
        return publisherRepository.findAllById(publisherIds);
    }

    public Page<Publisher> getAllPaged(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }
    
    public Publisher insert(PublisherRequest request) {
        Publisher publisher = PublisherMapper.toPublisher(request);
        publisher.setCreatedAt(LocalDateTime.now());
        publisher.setUpdatedAt(null);
        publisher.setDeletedAt(null);

        return publisherRepository.save(publisher);
    }

    public Publisher getById(Long id) {
        return publisherRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(
                "Publisher dengan ID " + id + " tidak ditemukkan"
        ));
    }

    public Publisher patchUpdate(Long id, PublisherPatchRequest patchRequest) {
        if (patchRequest == null) 
            throw new BadRequestException("Patch tidak boleh kosong sama sekali");
        
        Publisher publisher = getById(id);
        publisher = PublisherMapper.patch(patchRequest, publisher);
        publisher.setUpdatedAt(LocalDateTime.now());

        return publisherRepository.save(publisher);
    }

    public void deleteById(Long id) {
        Publisher publisher = getById(id);
        publisher.setDeletedAt(LocalDateTime.now());
        publisherRepository.save(publisher);
    }
    
}
