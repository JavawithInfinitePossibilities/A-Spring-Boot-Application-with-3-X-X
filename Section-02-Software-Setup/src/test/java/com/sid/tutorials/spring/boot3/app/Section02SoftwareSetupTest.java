package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.bean.Car;
import com.sid.tutorials.spring.boot3.app.bean.Person;
import com.sid.tutorials.spring.boot3.app.mockdata.MockDataPrep;
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
@SpringBootTest(classes = Section02SoftwareSetup.class)
class Section02SoftwareSetupTest {
    @Autowired
    private MockDataPrep mockDataPrep;

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

    /*@Disabled*/
    @Test
    void getCars() {
        try {
            List<Car> cars = mockDataPrep.getCars();
            cars.stream().forEach(car -> System.out.println(car.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}