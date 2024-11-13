package com.ECommerceApp.ECommerceApp.controller;
import com.ECommerceApp.ECommerceApp.model.Category;
import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.service.CategoryService;
import com.ECommerceApp.ECommerceApp.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("api/")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;
    private final CategoryService categoryService;

    ProductController (ProductServiceImpl productServiceImpl, CategoryService categoryService) {
        this.productServiceImpl = productServiceImpl;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productServiceImpl.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productServiceImpl.getProduct(productId);
    }

    @GetMapping("/product/sku/{productSKU}")
    public Product getProductBySku(@PathVariable String productSKU) {
        return productServiceImpl.getProductBySku(productSKU);
    }

    @GetMapping("/products/category/{productCategory}")
    public Product getProductsByCategory(@PathVariable String productCategory) {
        Category category = categoryService.findByName(productCategory);
        return productServiceImpl.getProductByCategory(category);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return productServiceImpl.createProduct(product);
    }

}