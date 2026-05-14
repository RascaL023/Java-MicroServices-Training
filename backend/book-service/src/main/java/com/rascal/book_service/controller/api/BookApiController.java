package com.rascal.book_service.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rascal.book_service.dto.mapper.BookMapper;
import com.rascal.book_service.dto.request.BookPatchRequest;
import com.rascal.book_service.dto.request.BookRequest;
import com.rascal.book_service.service.BookService;
import com.rascal.book_service.util.MessageResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookApiController {

    // TODO: Make jwt intregation
    @Autowired private BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllPaged(Pageable pageable) {
        return MessageResponse.success2xxPaged(
            HttpStatus.OK, 
            bookService.getAllPaged(pageable)
                .map(BookMapper::toResponse)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return MessageResponse.success2xx(
            HttpStatus.OK, 
            BookMapper.toResponse(bookService.getById(id))
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insert(
        @Valid @RequestBody BookRequest request
    ) {
        return MessageResponse.success2xx(
            HttpStatus.CREATED, 
            BookMapper.toResponse(bookService.insert(request))
        );
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('book.update')")
    public ResponseEntity<?> patch(
        @PathVariable Long id,
        @Valid @RequestBody
        BookPatchRequest patchRequest
    ) {
        return MessageResponse.success2xx(
            HttpStatus.OK, 
            BookMapper.toResponse(
                bookService.patchUpdate(id, patchRequest))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
