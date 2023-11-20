package org.example.service;

import org.example.model.Label;
import org.example.model.Post;

import java.util.List;

public interface LabelService {
    Label findById(Integer id);
    List<Label> findAll();
    String insert(Label entity);
    String removeById(Integer id);
}
