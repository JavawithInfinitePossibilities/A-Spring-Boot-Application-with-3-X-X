package com.sid.tutorials.spring.boot3.app.provider;

import com.sid.tutorials.spring.boot3.app.module.Product;
import com.sid.tutorials.spring.boot3.app.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author kunmu On 28-12-2023
 */
@Component
public class ProductProvider {

    @Autowired
    private ProductServices productServices;

    public Flux<Product> provideProduct() {
        return productServices.getProduct().map(this::myUpperCase);
    }

    private Product myUpperCase(Product product) {
        product.setName(product.getName().toUpperCase());
        return product;
    }
}
