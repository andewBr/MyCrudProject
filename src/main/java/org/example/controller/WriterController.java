package org.example.controller;

import org.example.service.PostService;

public class WriterController {

    private final PostService postService;

    public WriterController(PostService postService) {
        this.postService = postService;
    }

    public void findWriterById(Integer id) {
        System.out.println(postService.findById(id));
    }

//    List<Writer> findAll() {
//
//    }

//    Writer save(Writer entity) {
//
//    }

    void removeById(Integer id) {

    }



}
