package com.rascal.book_service.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rascal.book_service.dto.mapper.WriterMapper;
import com.rascal.book_service.dto.request.WriterPatchRequest;
import com.rascal.book_service.dto.request.WriterRequest;
import com.rascal.book_service.entity.Writer;
import com.rascal.book_service.exception.BadRequestException;
import com.rascal.book_service.exception.NotFoundException;
import com.rascal.book_service.repository.WriterRepository;
import com.rascal.book_service.service.WriterService;

@Service
public class WriterServiceNoCache implements WriterService {

    @Autowired private WriterRepository writerRepository;

    public List<Writer> getWriterByIds(List<Long> writerIds) {
        return writerRepository.findAllById(writerIds);
    }

    public Writer insert(WriterRequest request) {
        Writer writer = WriterMapper.toWriter(request);
        writer.setCreatedAt(LocalDateTime.now());
        writer.setUpdatedAt(null);
        writer.setDeletedAt(null);

        writer = writerRepository.save(writer);

        return writer;
    }
    
    public Writer getById(Long id) {
        Writer writer = writerRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(
                "Penulis dengan ID " + id + " tidak ditemukkan"
            ));

        return writer;
    }

    public Page<Writer> getAllPaged(Pageable pageable) {
        return writerRepository.findAll(pageable);
    }

    public Writer patchUpdate(Long id, WriterPatchRequest patchRequest) {
        if (patchRequest.isEmptyPatch()) 
            throw new BadRequestException("Patch tidak boleh kosong sama sekali");
        
        Writer writer = getById(id);
        writer = WriterMapper.patch(patchRequest, writer);
        writer.setUpdatedAt(LocalDateTime.now());

        writer = writerRepository.save(writer);
        return writer;
    }

    public void deleteById(Long id) {
        Writer writer = getById(id);
        writer.setDeletedAt(LocalDateTime.now());

        writer = writerRepository.save(writer);
    }

}

