package org.example.repository;

import org.example.model.Post;

public interface PostRepository extends GenericRepository<Post, Integer>{
    int insert2(Post entity, int LabelId);
}
