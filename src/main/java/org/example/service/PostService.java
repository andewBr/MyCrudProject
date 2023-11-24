package org.example.service;

import org.example.model.Post;
import org.example.model.Writer;

import java.util.List;

public interface PostService {
    Post findById(Integer id);
    List<Post> findAll();
    int insert(Post entity, int LabelId);
    String removeById(Integer id);
}
