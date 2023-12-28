package com.sid.tutorials.spring.boot3.app.repository;

import java.util.List;

import com.sid.tutorials.spring.boot3.app.entity.StudentDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author kunmu
 */
@Repository
public interface IStudentRepository extends JpaRepository<StudentDB, Long> {

    public List<StudentDB> findByName(String name);

    @Query(value = "Select s from studentdb s where name like %:name% and test_score=:score", nativeQuery = true)
    public List<StudentDB> findByNameLikeAndScore(@Param("name") String name, @Param("score") int score);
}
