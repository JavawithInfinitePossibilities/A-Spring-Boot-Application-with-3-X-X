/**
 *
 */
package com.sid.tutorials.spring.boot3.app.controller;

import com.sid.tutorials.spring.boot3.app.entity.StudentDB;
import com.sid.tutorials.spring.boot3.app.repository.IStudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @author kunmu URL :
 * http://localhost:8080/Studentapi/Studentdetails/CreateStudent/<br/>
 * http://localhost:8080/Studentapi/Studentdetails/StudentSave/ <br/>
 * { "id": 4, "name": "Sus", "testScore": 50 }
 * http://localhost:8080/Studentapi/Studentdetails/Students/
 * http://localhost:8080/Studentapi/Studentdetails/StudentById/2
 * http://localhost:8080/Studentapi/Studentdetails/StudentByName/Sid
 * http://localhost:8080/Studentapi/Studentdetails/StudentByNameAndScore/Sid&50
 * http://localhost:8080/Studentapi/Studentdetails/StudentUpdate/
 */
@Controller
@RequestMapping(value = "/Studentdetails")
public class StudentRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRESTController.class);

    @Autowired
    private IStudentRepository studentRepository;

    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/hello")
    public String helloSpringBoot() {
        return "hello";
    }

    @RequestMapping("/senddata")
    public ModelAndView sendData() {
        ModelAndView mav = new ModelAndView("data");
        mav.addObject("Message", "Take up one idea and make it your life");
        return mav;
    }

    @RequestMapping(value = "/Students/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    @Cacheable(value = "student-cache")
    public ModelAndView getAllStudent() {
        ModelAndView mav = new ModelAndView("studentdetails");
        List<StudentDB> studentDBList = studentRepository.findAll();
        mav.addObject("students", studentDBList);
        return mav;
    }

    @RequestMapping(value = "/StudentById/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @Cacheable(value = "student-cache")
    public ModelAndView getProduct(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("student");
        LOGGER.info("Finding Students by id : " + id);
        StudentDB studentDB = studentRepository.findById(id).get();
        mav.addObject("student", studentDB);
        return mav;
    }

    @RequestMapping(value = "/StudentByName/{name}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @Cacheable(value = "student-cache")
    public List<StudentDB> getProductByName(@PathVariable("name") String name) {
        LOGGER.info("Finding Students by name : " + name);
        return studentRepository.findByName(name);
    }

    @RequestMapping(value = "/StudentByNameAndScore/{name}&{score}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @Cacheable(value = "student-cache")
    public List<StudentDB> getProductByNameAndPrice(@PathVariable("name") String name,
                                                    @PathVariable("score") int score) {
        LOGGER.info("Finding Students by name : " + name + " and Score :" + score);
        return studentRepository.findByNameLikeAndScore(name, score);
    }

    @RequestMapping("/CreateStudent/")
    public ModelAndView createProduct(@ModelAttribute("student") StudentDB student) {
        System.out.println("Creating student details :::");
        ModelAndView mav = new ModelAndView("studentsubmit");
        student.setId(1);
        student.setName("Sid");
        student.setTestScore(100);
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping(value = "/StudentSave/", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute("student") StudentDB studentDB) {
        ModelAndView mav = new ModelAndView("student");
        StudentDB saved = studentRepository.save(studentDB);
        mav.addObject("student", saved);
        return mav;
    }

    @RequestMapping(value = "/StudentUpdate/", method = RequestMethod.PUT)
    public StudentDB updateProduct(@RequestBody StudentDB product) {
        return studentRepository.save(product);
    }

    @RequestMapping(value = "/StudentDelete/{id}", method = RequestMethod.DELETE)
    @Transactional(readOnly = true)
    @CacheEvict(value = "student-cache")
    public void deleteProduct(@PathVariable("id") long id) {
        studentRepository.deleteById(id);
    }
}
