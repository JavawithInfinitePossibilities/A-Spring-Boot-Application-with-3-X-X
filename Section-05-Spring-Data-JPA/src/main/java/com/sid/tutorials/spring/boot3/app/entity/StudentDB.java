/**
 *
 */
package com.sid.tutorials.spring.boot3.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;


/**
 * @author kunmu
 *
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDB {
    @Id
    private long id;
    private String name;
    private int testScore;
}
