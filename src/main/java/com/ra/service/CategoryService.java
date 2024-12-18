package com.ra.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);


    void delete(Long id);
}
