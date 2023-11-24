package org.example.repository;

import java.util.List;

public interface GenericRepository<T, ID>{
    T findById(ID id);
    List<T> findAll();
    int insert(T entity);
    String removeById(ID id);
}
