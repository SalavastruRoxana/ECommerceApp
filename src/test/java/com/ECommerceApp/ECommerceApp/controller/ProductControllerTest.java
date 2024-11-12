package com.ECommerceApp.ECommerceApp.controller;

import com.ECommerceApp.ECommerceApp.model.Category;
import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
//@SpringBootTest(classes = ProductController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productServiceImpl;

    private Product product;
    private Category category;

    @BeforeEach
    void setUp() {
        product = new Product();
        category = new Category();
        category.setId(1);
        category.setName("Category 1");
        product.setCategory(category);
        product.setId(1);
        product.setSku("1-ww-0101");
        product.setDescription("Description 1");
        product.setPrice(Float.valueOf("250.00"));
        product.setStock(100);
    }


    @Test
    public void ProductController_CreateProduct() throws Exception {
        this.mockMvc.perform( post("/product")
                        .content(asJsonString(new Product(1, this.category, "1-ww-0101",
                                "Description 1", Float.valueOf("250.00"), 100)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.productId").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
