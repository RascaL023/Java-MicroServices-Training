package com.rascal.book_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rascal.book_service.entity.Writer;

public interface WriterRepository extends JpaRepository<Writer, Long> {


    
}
