package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.entities.Product;
import com.sid.tutorials.spring.boot3.app.product.IProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kunmu On 28-12-2023
 */
@SpringBootTest(classes = Section15UnitTestingusingMockMvc.class)
@AutoConfigureMockMvc
class Section15UnitTestingusingMockMvcTest {
    private static final String PRODUCTS = "products/";

    private static final String PRODUCT_SAVE = "productSave/";

    private static final String PRODUCTCART_URL = "/productmart";

    private static final String PRODUCTCART_URI = "/productmart/productdetails/";

    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private IProductRepository productRepository;

    @Disabled
    @Test
    public void testFindAll() {
        Product product = buildProduct();
        List<Product> asList = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(asList);
        ObjectWriter prettyPrinter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            mockMVC.perform(get(PRODUCTCART_URI + PRODUCTS).contextPath(PRODUCTCART_URL))
                    .andExpect(status().isOk()).andExpect(content()
                            .json(prettyPrinter.writeValueAsString(asList)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("This is Mock MVC test");
    }

    @Disabled
    @Test
    public void testCreateProduct() {
        Product product = buildProduct();
        when(productRepository.save(any())).thenReturn(product);
        ObjectWriter prettyPrinter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            mockMVC.perform(post(PRODUCTCART_URI + PRODUCT_SAVE).contextPath(PRODUCTCART_URL)
                            .contentType(MediaType.APPLICATION_JSON).content(prettyPrinter.writeValueAsString(product)))
                    .andExpect(status().isOk()).andExpect(content().json(prettyPrinter.writeValueAsString(product)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("This is Mock MVC testCreateProduct");
    }

    private Product buildProduct() {
        Product product = Product.builder().id(1).name("Dumy Name").description("Dumy description").price(1000d)
                .build();
        return product;
    }
}