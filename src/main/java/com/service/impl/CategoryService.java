package com.service.impl;

import com.model.Category;
import com.repository.CategoryRepository;
import com.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category T) {
        return categoryRepository.save(T);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }
}
