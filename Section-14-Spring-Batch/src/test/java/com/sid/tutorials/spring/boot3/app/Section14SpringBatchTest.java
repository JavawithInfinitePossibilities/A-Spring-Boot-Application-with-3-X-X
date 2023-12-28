package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.bean.Car;
import com.sid.tutorials.spring.boot3.app.bean.Person;
import com.sid.tutorials.spring.boot3.app.entity.Product;
import com.sid.tutorials.spring.boot3.app.entity.StudentDB;
import com.sid.tutorials.spring.boot3.app.mockdata.MockDataPrep;
import com.sid.tutorials.spring.boot3.app.repository.IStudentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kunmu On 28-12-2023
 */
@SpringBootTest(classes = Section14SpringBatch.class)
@SpringBatchTest
@SpringJUnitConfig(CustomConfigForTest.class)
class Section14SpringBatchTest {
    @Autowired
    private MockDataPrep mockDataPrep;

    @Autowired
    private IStudentRepository studentRepo;

    @Autowired
    @Qualifier("primaryJobLauncherTestUtils")
    private JobLauncherTestUtils jobLauncherTestUtils;

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

    @Disabled
    @Test
    void testSaveStudent() {
        StudentDB studentDB = new StudentDB();
        /*studentDB.setId(1l);*/
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

    @Disabled("Disable to make the test cases more visible")
    @Test
    void getAllProduct() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> productArr = restTemplate.getForEntity(bashURL + "products/", Product[].class);
        List<Product> products = Arrays.asList(productArr.getBody());
        products.stream().forEach(System.out::println);
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

    @Disabled
    @Test
    void updateProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(bashURL + "productById/5", Product.class);
        System.out.println("product.getName() :" + product.getName());
        product.setPrice(2000);
        restTemplate.put(bashURL + "productUpdate/", product);
    }

    @Test
    void springBatchTest() {
        JobExecution jobExecution = null;
        try {
            jobExecution = jobLauncherTestUtils.launchJob();
            assertEquals("mySpringBatchFirstJob", jobExecution.getJobInstance().getJobName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}