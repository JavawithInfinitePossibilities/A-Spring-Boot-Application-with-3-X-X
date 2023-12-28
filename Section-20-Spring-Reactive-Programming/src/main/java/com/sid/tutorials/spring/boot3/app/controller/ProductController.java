package com.sid.tutorials.spring.boot3.app.controller;

import com.sid.tutorials.spring.boot3.app.module.Product;
import com.sid.tutorials.spring.boot3.app.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author kunmu On 28-12-2023
 * http://localhost:8080/productdetails/productflux
 */
@Controller
@RequestMapping("/productdetails")
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @GetMapping("/productflux")
    public String getProduct(Model model) {
        model.addAttribute("products", productServices.getProduct());
        return "productdetails";
    }
}
