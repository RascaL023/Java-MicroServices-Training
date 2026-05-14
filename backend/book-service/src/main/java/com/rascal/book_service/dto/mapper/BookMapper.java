package com.rascal.book_service.dto.mapper;

import java.util.List;

import com.rascal.book_service.dto.request.BookPatchRequest;
import com.rascal.book_service.dto.request.BookRequest;
import com.rascal.book_service.dto.response.BookResponse;
import com.rascal.book_service.entity.Book;
import com.rascal.book_service.entity.Publisher;
import com.rascal.book_service.entity.Writer;

public class BookMapper {

    public static Book toBook(
        BookRequest request, 
        List<Writer> writers,
        List<Publisher> publishers
    ) {
        Book book = new Book();

        book.setTitle(request.title());
        book.setPublishedYear(request.publishedYear());
        book.setWriters(writers);
        book.setPublishers(publishers);

        return book;
    }

    public static BookResponse toResponse(Book book) {
        BookResponse bookResponse = new BookResponse(
            book.getId(), 
            book.getTitle(), 
            book.getPublishedYear(), 
            book.getWriters().stream().
                map(Writer::getName).toList(), 
            book.getPublishers().stream().
                map(Publisher::getName).toList(),
            book.getCreatedAt(), 
            book.getUpdatedAt(), 
            book.getDeletedAt()
        );

        return bookResponse;
    }
 
    public static Book patch(
        BookPatchRequest patchRequest,
        Book book
    ) {
        if (!patchRequest.isEmptyTitle())
            book.setTitle(patchRequest.title());
        if (!patchRequest.isEmptyPublihsedYear())
            book.setPublishedYear(patchRequest.publishedYear());

        return book;
    }

}
