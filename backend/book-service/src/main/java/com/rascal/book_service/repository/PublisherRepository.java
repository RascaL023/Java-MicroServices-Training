package com.rascal.book_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rascal.book_service.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {


    
}
