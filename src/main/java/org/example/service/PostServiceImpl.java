package org.example.service;

import org.example.model.Post;
import org.example.repository.PostRepository;

import java.util.List;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public String insert(Post entity) {
        return postRepository.insert(entity);
    }

    @Override
    public String removeById(Integer id) {
        return postRepository.removeById(id);
    }
}
