package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Category;
import com.ECommerceApp.ECommerceApp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category findById(int id) {
        return this.categoryRepository.findById(id);
    }
    public void delete(int id) {
        this.categoryRepository.delete(categoryRepository.findById(id));
    }
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }
}
