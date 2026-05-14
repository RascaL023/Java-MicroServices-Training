package com.rascal.book_service.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rascal.book_service.dto.mapper.BookMapper;
import com.rascal.book_service.dto.request.BookPatchRequest;
import com.rascal.book_service.dto.request.BookRequest;
import com.rascal.book_service.entity.Book;
import com.rascal.book_service.entity.Publisher;
import com.rascal.book_service.entity.Writer;
import com.rascal.book_service.exception.BadRequestException;
import com.rascal.book_service.exception.NotFoundException;
import com.rascal.book_service.repository.BookRepository;
import com.rascal.book_service.service.BookService;
import com.rascal.book_service.service.PublisherService;
import com.rascal.book_service.service.WriterService;

@Service
public class BookServiceNoCache implements BookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private WriterService writerService;
    @Autowired private PublisherService publisherService;

    private List<Writer> validateAndGetWriterByIds(List<Long> writerIds) {
        List<Writer> writers = writerService.getWriterByIds(writerIds);
        if (writerIds.size() == writers.size()) return writers;


        Set<Long> foundIds = writers.stream()
           .map(Writer::getId).collect(Collectors.toSet());

        List<Long> missingIds = writerIds.stream()
            .filter(id -> !foundIds.contains(id)).toList();

        throw new NotFoundException("ID Penulis tidak ditemukkan: " + missingIds);

    }

    private List<Publisher> validateAndGetPublisherByIds(List<Long> publisherIds) {
        List<Publisher> publishers = publisherService.getPublisherByIds(publisherIds);
        if (publisherIds.size() == publishers.size()) return publishers;


        Set<Long> foundIds = publishers.stream()
        .map(Publisher::getId).collect(Collectors.toSet());

        List<Long> missingIds = publisherIds.stream()
        .filter(id -> !foundIds.contains(id)).toList();

        throw new NotFoundException("ID Publisher tidak ditemukkan: " + missingIds);
    }

    

    public Book getById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Buku tidak ditemukan"));
    }

    public Page<Book> getAllPaged(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book insert(BookRequest request) {
        List<Writer> writers = validateAndGetWriterByIds(request.writerIds());
        List<Publisher> publishers = validateAndGetPublisherByIds(request.publisherIds());

        Book book = BookMapper.toBook(request, writers, publishers);
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(null);
        book.setDeletedAt(null);

        return bookRepository.save(book);
    }

    public Book patchUpdate(Long id, BookPatchRequest patchRequest) {
        if (patchRequest.isEmptyPatch()) 
            throw new BadRequestException("Patch tidak boleh kosong");

        Book book = getById(id);
        if (!patchRequest.isEmptyWriterIds())
            book.setWriters(validateAndGetWriterByIds(
                patchRequest.writerIds()
            ));
        if (!patchRequest.isEmptyPublisherIds())
            book.setPublishers(validateAndGetPublisherByIds(
                patchRequest.publisherIds()
            ));

        book = BookMapper.patch(patchRequest, book);
        book.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        Book book = getById(id);
        book.setDeletedAt(LocalDateTime.now());
        bookRepository.save(book);
    }
}
