package org.example.view;

import org.example.model.Label;
import org.example.repository.LabelRepositoryImpl;
import org.example.service.LabelServiceImpl;

public class LabelView {

    private LabelServiceImpl labelService;

    public LabelView() {
        this.labelService = new LabelServiceImpl(new LabelRepositoryImpl());
    }

    public void findById(Integer id) {
        System.out.println("Label find by id: " + labelService.findById(id));
    }
    public void findAll() {
        System.out.println("Label findAll: " + labelService.findAll());
    }
    public void insert(Label entity) {
        System.out.println(labelService.insert(entity));
    }
    public void removeById(Integer id) {
        System.out.println("Label remove by id: " + labelService.removeById(id));
    }
}
