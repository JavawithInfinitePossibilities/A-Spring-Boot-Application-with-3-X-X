/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.repository;

import com.sid.tutorials.spring.boot3.app.entity.StudentDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author kunmu
 *
 */
@Repository
public interface IStudentRepository extends JpaRepository<StudentDB, Long> {
}
