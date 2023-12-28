package com.sid.tutorials.spring.boot3.app.services;

import com.sid.tutorials.spring.boot3.app.module.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author kunmu On 28-12-2023
 */
@Service
public class ProductServices {

    public Flux<Product> getProduct() {
        return Flux.just(Product.builder().id(1).price(1000).description("phone").name("Iphone").build()
                , Product.builder().id(2).price(1000).description("tab").name("Ipad").build()
                , Product.builder().id(3).price(1000).description("watch").name("Apple watch").build()
                , Product.builder().id(4).price(1000).description("phone").name("Galexy").build()
                , Product.builder().id(5).price(1000).description("tab").name("Galexy ultra").build()
                , Product.builder().id(6).price(1000).description("watch").name("Samsung watch").build());
    }
}
