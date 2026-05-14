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

import com.rascal.book_service.dto.mapper.WriterMapper;
import com.rascal.book_service.dto.request.WriterPatchRequest;
import com.rascal.book_service.dto.request.WriterRequest;
import com.rascal.book_service.service.WriterService;
import com.rascal.book_service.util.MessageResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/writers")
public class WriterApiController {

    @Autowired private WriterService writerService;

    @GetMapping
    public ResponseEntity<?> getAllPaged(Pageable pageable) {
        return MessageResponse.success2xxPaged(
            HttpStatus.OK, 
            writerService.getAllPaged(pageable)
                .map(WriterMapper::toResponse)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return MessageResponse.success2xx(
            HttpStatus.OK, 
            WriterMapper.toResponse(writerService.getById(id))
        );
    }

    @PostMapping 
    public ResponseEntity<?> insert(
        @Valid @RequestBody WriterRequest request
    ) {
        return MessageResponse.success2xx(
            HttpStatus.CREATED,
            WriterMapper.toResponse(writerService.insert(request))
        );
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(
        @PathVariable Long id,
        @Valid @RequestBody
        WriterPatchRequest patchRequest
    ) {
        return MessageResponse.success2xx(
            HttpStatus.OK, 
            WriterMapper.toResponse(
                writerService.patchUpdate(id, patchRequest))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        writerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
