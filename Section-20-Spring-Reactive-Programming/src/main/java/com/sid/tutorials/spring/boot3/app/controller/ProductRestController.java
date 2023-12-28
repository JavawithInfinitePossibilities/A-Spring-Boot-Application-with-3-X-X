package com.sid.tutorials.spring.boot3.app.controller;

import com.sid.tutorials.spring.boot3.app.module.Product;
import com.sid.tutorials.spring.boot3.app.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author kunmu On 28-12-2023
 * http://localhost:8080/productcart/productdetailsrest/productflux
 */
@RestController
@RequestMapping("/productdetailsrest")
public class ProductRestController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("/productflux")
    public Flux<Product> getProduct(){
        return productServices.getProduct();
    }
}
