package org.example.service;

import org.example.model.Label;
import org.example.repository.LabelRepository;

import java.util.List;

public class LabelServiceImpl implements LabelService {

    private LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label findById(Integer id) {
        return labelRepository.findById(id);
    }

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public String insert(Label entity) {
        return labelRepository.insert(entity);
    }

    @Override
    public String removeById(Integer id) {
        return labelRepository.removeById(id);
    }
}
