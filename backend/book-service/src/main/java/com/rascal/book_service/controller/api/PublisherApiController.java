package com.rascal.book_service.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rascal.book_service.dto.mapper.PublisherMapper;
import com.rascal.book_service.dto.request.PublisherPatchRequest;
import com.rascal.book_service.dto.request.PublisherRequest;
import com.rascal.book_service.service.PublisherService;
import com.rascal.book_service.util.MessageResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publishers")
public class PublisherApiController {

    @Autowired private PublisherService publisherService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return MessageResponse.success2xx(
            HttpStatus.OK, 
            PublisherMapper.toResponse(
                publisherService.getById(id)
            )
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllPaged(Pageable pageable) {
        return MessageResponse.success2xxPaged(
            HttpStatus.OK, 
            publisherService.getAllPaged(pageable)
                .map(PublisherMapper::toResponse)
        );
    }

    @PostMapping 
    public ResponseEntity<?> insert(
        @Valid @RequestBody PublisherRequest request
    ) {
        return MessageResponse.success2xx(
            HttpStatus.CREATED,
            PublisherMapper.toResponse(
                publisherService.insert(request)
            )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(
        @PathVariable Long id,
        @Valid @RequestBody
        PublisherPatchRequest patchRequest
    ) {
        return MessageResponse.success2xx(
            HttpStatus.OK, 
            PublisherMapper.toResponse(
                publisherService.patchUpdate(id, patchRequest))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
