package com.rascal.book_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rascal.book_service.dto.request.BookPatchRequest;
import com.rascal.book_service.dto.request.BookRequest;
import com.rascal.book_service.entity.Book;

public interface BookService {

    Book getById(Long id);
    Page<Book> getAllPaged(Pageable pageable);
    Book insert(BookRequest request);
    Book patchUpdate(Long id, BookPatchRequest patchRequest);
    void deleteById(Long id);
    
}
