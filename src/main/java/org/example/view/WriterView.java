package org.example.view;

import org.example.model.Writer;
import org.example.repository.WriterRepositoryImpl;
import org.example.service.WriterService;
import org.example.service.WriterServiceImpl;

import java.util.List;

public class WriterView {

    private WriterService writerService;

    public WriterView() {
        this.writerService = new WriterServiceImpl(new WriterRepositoryImpl());
    }

    public void findById(Integer id) {
        System.out.println("Writer findById: " + writerService.findById(id));
    }
    public void findAll() {
        System.out.println("Writer findAll: " + writerService.findAll());
    }
    public void save(Writer entity) {
        System.out.println("Writer save:" + writerService.save(entity));
    }
    public void removeById(Integer id) {
        System.out.println("Writer removeById " + writerService.removeById(id));
    }
}
