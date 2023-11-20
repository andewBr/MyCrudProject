package org.example.service;

import org.example.model.Writer;
import org.example.repository.WriterRepository;

import java.util.List;

public class WriterServiceImpl implements WriterService {

    private WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Writer findById(Integer id) {
        return writerRepository.findById(id);

    }

    @Override
    public List<Writer> findAll() {
        return writerRepository.findAll();
    }

    @Override
    public String save(Writer entity) {
        return writerRepository.insert(entity);
    }

    @Override
    public String removeById(Integer id) {
        return writerRepository.removeById(id);
    }
}
