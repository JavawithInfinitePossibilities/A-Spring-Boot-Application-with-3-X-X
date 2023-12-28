package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.bean.Car;
import com.sid.tutorials.spring.boot3.app.bean.Person;
import com.sid.tutorials.spring.boot3.app.entity.Product;
import com.sid.tutorials.spring.boot3.app.entity.StudentDB;
import com.sid.tutorials.spring.boot3.app.mockdata.MockDataPrep;
import com.sid.tutorials.spring.boot3.app.repository.IStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kunmu On 26-12-2023
 */
@SpringBootTest(classes = Section09Logging.class)
@Slf4j
class Section09LoggingTest {
    @Autowired
    private MockDataPrep mockDataPrep;

    @Autowired
    private IStudentRepository studentRepo;

    @Value("${productrestapi.services.url}")
    private String bashURL;

    @Disabled
    @Test
    void getPeople() {
        try {
            List<Person> people = mockDataPrep.getPeople();
            people.stream().forEach(p -> System.out.println(p.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Disabled
    @Test
    void getCars() {
        try {
            List<Car> cars = mockDataPrep.getCars();
            cars.stream().forEach(car -> System.out.println(car.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSaveStudent() {
        StudentDB studentDB = new StudentDB();
        studentDB.setId(1l);
        studentDB.setName("Siddhant");
        studentDB.setTestScore(100);

        studentRepo.save(studentDB);

        // Select
        StudentDB saveStudent = studentRepo.findById(1l).get();
        System.out.println(saveStudent);

        // Update

        studentDB.setTestScore(90);
        studentRepo.save(studentDB);
        StudentDB updateStudent = studentRepo.findById(1l).get();
        System.out.println("Updated student :" + updateStudent);

        // Delete
        /*
         * studentRepo.deleteById(1L); StudentDB deleteStudent =
         * studentRepo.findById(1l).orElse(null); System.out.println("Deleted student:"
         * + deleteStudent);
         */

    }

    /* @Disabled("Disable to make the test cases more visible")*/
    @Test
    void getAllProduct() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> productArr = restTemplate.getForEntity(bashURL + "products/", Product[].class);
        List<Product> products = Arrays.asList(productArr.getBody());
        products.stream().forEach(product -> log.info("Product details : {}", product));
    }

    @Disabled
    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(bashURL + "productById/1", Product.class);
        System.out.println("product.getName() :" + product);
    }

    @Disabled
    @Test
    void getProductByName() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> productArr = restTemplate.getForEntity(bashURL + "productByName/vaporizer",
                Product[].class);
        List<Product> products = Arrays.asList(productArr.getBody());
        products.stream().forEach(System.out::println);
    }

    @Disabled
    @Test
    void getProductByNameAndPrice() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> productArr = restTemplate.getForEntity(bashURL + "productByNameAndPrice/Ip&900",
                Product[].class);
        List<Product> products = Arrays.asList(productArr.getBody());
        products.stream().forEach(System.out::println);
    }

    @Disabled
    @Test
    void saveProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = Product.builder().name("Apple TV").description("TV").price(7000d).build();
        Product product2 = restTemplate.postForObject(bashURL + "productSave/", product, Product.class);
        System.out.println("product.getName() :" + product2.getName());
    }

    @Test
    void updateProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(bashURL + "productById/5", Product.class);
        System.out.println("product.getName() :" + product.getName());
        product.setPrice(2000);
        restTemplate.put(bashURL + "productUpdate/", product);
    }
}