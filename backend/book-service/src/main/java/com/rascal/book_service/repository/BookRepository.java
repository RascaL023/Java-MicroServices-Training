package com.rascal.book_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rascal.book_service.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Book -> nama object, bukan table nya!!!
    @Query("SELECT DISTINCT b FROM Book b JOIN FETCH b.writers JOIN FETCH b.publishers")
    List<Book> findAll();    

}
