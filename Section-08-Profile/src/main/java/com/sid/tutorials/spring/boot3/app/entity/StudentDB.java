/**
 *
 */
package com.sid.tutorials.spring.boot3.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


/**
 * @author kunmu
 *
 */
@Entity(name = "studentdb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "fname")
    private String name;
    @Column(name = "test_score")
    private int testScore;
}
