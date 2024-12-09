package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//import org.springframework.mail.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundTasksScheduler {

    private final ProductService productService;
    private List<Product> outOfStockProducts;
    //private JavaMail

    //TODO Autowired to constructor
    @Autowired
    public BackgroundTasksScheduler(ProductService productService) {
        this.productService = productService;
    }

    //TODO I can use it scheduled to check if any other product got out of stock
    private void populateOutOfStockProducts() {
        outOfStockProducts = new ArrayList<>();
        List<Product> existingProducts = productService.getProducts();
        for (Product product : existingProducts) {
            if (product.getStock() == 0) {
                outOfStockProducts.add(product);
            }
        }
    }

    public void addOutOfStockProduct(Product product) {
        this.outOfStockProducts.add(product);
    }



    //TODO: check stock every 5 minutes, inform customers
    @Scheduled(fixedRate = 1000)
    public void checkStock()
    {
        for (Product product : outOfStockProducts) {
            if (product.getStock() > 0) {

            }
        }
    }

    //TODO: Check mail
    // EMAIL_PATTERN = "^[_A-Za-z0-9-+]+
    //        (.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*
    //        (.[A-Za-z]{2,})$"
    // Pattern.compile and matcher
    private void sendMail(Customer customer, Product product) {
        String recipientAddress = customer.getEmail();
        String subject = "Product back in stock";
        String message = "The product you've been looking for is now back in stock \n " + product.getDescription();

//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message);
//        mailSender.send(email);

    }


    //TODO: add deals table containing 2 columns that describe the time
    // frame of availability, send mails to customers, maybe send file format in the mail from lovcal files with an jpg and so on everyday at 7:00
}
