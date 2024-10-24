package com.ECommerceApp.ECommerceApp.controller;

import com.ECommerceApp.ECommerceApp.service.CartService;
import com.ECommerceApp.ECommerceApp.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

}
