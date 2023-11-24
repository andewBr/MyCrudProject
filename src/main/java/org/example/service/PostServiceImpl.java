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
    public int insert(Post entity, int LabelId) {
        return postRepository.insert2(entity, LabelId);
    }

    @Override
    public String removeById(Integer id) {
        return postRepository.removeById(id);
    }
}
