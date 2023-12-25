package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.bean.Car;
import com.sid.tutorials.spring.boot3.app.bean.Person;
import com.sid.tutorials.spring.boot3.app.entity.StudentDB;
import com.sid.tutorials.spring.boot3.app.mockdata.MockDataPrep;
import com.sid.tutorials.spring.boot3.app.repository.IStudentRepository;
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
@SpringBootTest(classes = Section05SpringDataJPA.class)
class Section05SpringDataJPATest {
    @Autowired
    private MockDataPrep mockDataPrep;

    @Autowired
    private IStudentRepository studentRepo;


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
}