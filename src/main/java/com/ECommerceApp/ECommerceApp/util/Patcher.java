package com.ECommerceApp.ECommerceApp.util;

import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Product;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public static Customer customerPatcher(Customer existingCustomer, Customer incompleteCustomer) throws IllegalAccessException {
            Class<?> customerClass= Customer.class;
            Field[] customerFields= customerClass.getDeclaredFields();
            for(Field field : customerFields){
                field.setAccessible(true);

                Object value=field.get(incompleteCustomer);
                if(value!=null){
                    field.set(existingCustomer, value);
                }
                field.setAccessible(false);
            }
            return existingCustomer;
        }

    public static Product customerPatcher(Product existingProduct, Product incompleteProduct) throws IllegalAccessException {
        Class<?> productClass= Product.class;
        Field[] productFields= productClass.getDeclaredFields();
        for(Field field : productFields){
            field.setAccessible(true);

            Object value=field.get(incompleteProduct);
            if(value!=null){
                field.set(existingProduct, value);
            }
            field.setAccessible(false);
        }
        return existingProduct;
    }

}
