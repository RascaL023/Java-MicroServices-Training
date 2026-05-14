package com.rascal.book_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rascal.book_service.dto.request.WriterPatchRequest;
import com.rascal.book_service.dto.request.WriterRequest;
import com.rascal.book_service.entity.Writer;

public interface WriterService {

    List<Writer> getWriterByIds(List<Long> writerIds);
    Writer getById(Long id);
    Page<Writer> getAllPaged(Pageable pageable);
    Writer insert(WriterRequest request);
    Writer patchUpdate(Long id, WriterPatchRequest patchRequest);
    void deleteById(Long id);
    
}
