package org.example.service;

import org.example.model.Writer;

import java.util.List;

public interface WriterService {
    Writer findById(Integer id);
    List<Writer> findAll();
    String save(Writer entity);
    String removeById(Integer id);
}
