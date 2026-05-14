package com.rascal.book_service.service.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rascal.book_service.dto.mapper.WriterMapper;
import com.rascal.book_service.dto.request.WriterPatchRequest;
import com.rascal.book_service.dto.request.WriterRequest;
import com.rascal.book_service.entity.Writer;
import com.rascal.book_service.exception.BadRequestException;
import com.rascal.book_service.exception.NotFoundException;
import com.rascal.book_service.infrastructure.Redis;
import com.rascal.book_service.repository.WriterRepository;

@Service
public class WriterServiceWithCache {

    @Autowired private WriterRepository writerRepository;
    @Autowired private Redis redis;
    private final String key = "writer:";
    // TODO: DTO for Redis

    public List<Writer> getWriterByIds(List<Long> writerIds) {
        List<String> keys = writerIds.stream().map(id -> key + id).toList();
        List<Writer> writers = new ArrayList<>();
        List<Long> missingIds = new ArrayList<>();
        List<Object> caches = redis.mGet(keys);

        for (int i = 0; i < writerIds.size(); i++) {
            Writer writer = (Writer) caches.get(i);

            if (writer == null) missingIds.add(writerIds.get(i));
            else writers.add(writer);
        }

        if (!missingIds.isEmpty()) {
            List<Writer> repoWriters = writerRepository.findAllById(missingIds);

            for (Writer writer : repoWriters) {
                if (writer == null) {
                    System.out.println("Not found Writer!");
                    continue;
                }

                redis.set(key + writer.getId(), writer, 3);
            }

            writers.addAll(repoWriters);
        }

        return writers;
    }

    public Writer getById(Long id) {
        Writer writer = (Writer) redis.get(key + id);

        if (writer == null) {
            writer = writerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                    "Penulis dengan ID " + id + " tidak ditemukkan"
                ));

            redis.set(key + id, writer, 3);
        }

        return writer;
    }

    public Writer insert(WriterRequest request) {
        Writer writer = WriterMapper.toWriter(request);
        writer.setCreatedAt(LocalDateTime.now());
        writer.setUpdatedAt(null);
        writer.setDeletedAt(null);

        writer = writerRepository.save(writer);
        redis.set(key + writer.getId(), writer, 3);

        return writer;
    }

    public Writer patchUpdate(Long id, WriterPatchRequest patchRequest) {
        if (patchRequest.isEmptyPatch()) 
            throw new BadRequestException("Patch tidak boleh kosong sama sekali");
        
        Writer writer = getById(id);
        writer = WriterMapper.patch(patchRequest, writer);
        writer.setUpdatedAt(LocalDateTime.now());

        writer = writerRepository.save(writer);
        redis.set(key + writer.getId(), writer, 3);

        return writer;
    }

    public void deleteById(Long id) {
        Writer writer = getById(id);
        writer.setDeletedAt(LocalDateTime.now());

        writer = writerRepository.save(writer);
        redis.set(key + id, writer, 3);
    }

}
