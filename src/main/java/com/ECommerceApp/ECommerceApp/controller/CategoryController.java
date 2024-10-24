package com.ECommerceApp.ECommerceApp.controller;

import com.ECommerceApp.ECommerceApp.model.Category;
import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.service.CategoryService;
import com.ECommerceApp.ECommerceApp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public Iterable<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public Category getCategory(@PathVariable String categoryId) {
        return categoryService.findById(Integer.parseInt(categoryId));
    }

    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

}
