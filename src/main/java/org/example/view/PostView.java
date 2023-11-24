package org.example.view;

import org.example.model.Post;
import org.example.repository.PostRepositoryImpl;
import org.example.service.PostServiceImpl;

public class PostView {

    private PostServiceImpl postService;

    public PostView() {
        this.postService = new PostServiceImpl(new PostRepositoryImpl());
    }

    public void findById(Integer id) {
        System.out.println("post findById: " + postService.findById(id));
    }
    public void findAll() {
        System.out.println("post findAll: " + postService.findAll());
    }
    public void insert(Post entity, int labelId) {
        postService.insert(entity, labelId);
    }
    public void removeById(Integer id) {
        System.out.println(postService.removeById(id));
    }

}
