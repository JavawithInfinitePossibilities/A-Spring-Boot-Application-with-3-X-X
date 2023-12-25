package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.bean.Car;
import com.sid.tutorials.spring.boot3.app.bean.Person;
import com.sid.tutorials.spring.boot3.app.mockdata.MockDataPrep;
import com.sid.tutorials.spring.boot3.app.services.IPaymentServices;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kunmu On 25-12-2023
 */
@SpringBootTest(classes = Section04FirstProject.class)
class Section04FirstProjectTest {
    @Autowired
    private MockDataPrep mockDataPrep;

    @Autowired
    IPaymentServices iPaymentServices;

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
    void test() {
        assertNotNull(iPaymentServices, "Value is not null!!!");
    }

    /*@Disabled*/
    @Test
    void testCreate() {
        iPaymentServices.create();
    }

    @Disabled
    @Test
    void testUpdate() {
        iPaymentServices.update();
    }

    @Disabled
    @Test
    void testDelete() {
        iPaymentServices.delete();
    }

    @Disabled
    @Test
    void testSelect() {
        iPaymentServices.get();
    }
}