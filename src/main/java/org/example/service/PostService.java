package org.example.service;

import org.example.model.Post;
import org.example.model.Writer;

import java.util.List;

public interface PostService {
    Post findById(Integer id);
    List<Post> findAll();
    String insert(Post entity);
    String removeById(Integer id);
}
